package ru.catdimson.bjjmechanics.ui.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentTermsBinding
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.viewmodel.terms.TermsViewModel
import java.util.*


class TermsFragment : AbstractScreenFragment<FragmentTermsBinding>(FragmentTermsBinding::inflate) {

    private lateinit var viewModel: TermsViewModel
    override var scope = getKoin().getOrCreateScope("termsScope", named("termsScope"))
    private val adapter by lazy { TermsAdapter(onListItemClickListener) }
    private val authMap = mapOf(
        Pair(
            "Authorization",
            "${"Basic"} ${Base64.getEncoder().encodeToString("user:password".toByteArray())}"
        )
    )

    private val onListItemClickListener: TermsAdapter.OnListItemClickListener =
        object : TermsAdapter.OnListItemClickListener {
            override fun onItemClick(data: Term) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, TermsDetailFragment.newInstance(data.id))
                    .addToBackStack(null)
                    .commit()
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = TermsFragment()
    }

    private fun setDataToAdapter(data: List<Term>) {
        adapter.setData(data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerParts()
        initIncomingEvents()
        initOutgoingEvents()
    }

    private fun initViewModel() {
        if (binding.termsRecyclerView.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        viewModel = scope.get()
    }

    private fun initOutgoingEvents() {
        viewModel.onShowAllTerms(authMap)
    }

    private fun initIncomingEvents() {
        viewModel.subscribe().observe(requireActivity()) {
            renderData(it)
        }
    }

    private fun initRecyclerParts() {
        binding.termsRecyclerView.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessTerms -> {
                showViewWorking()
                appState.data.let {
                    if (it.isEmpty()) {
                        Toast.makeText(context, "Данных нет, сорян", Toast.LENGTH_LONG).show()
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.loading.apply {
                        progressBarRound.visibility = View.GONE
                    }
                } else {
                    binding.loading.apply {
                        progressBarRound.visibility = View.VISIBLE
                    }
                }
            }
            is AppState.Error -> {
                showViewWorking()
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show()
            }
            else -> {
                showViewWorking()
                Toast.makeText(context, "Что-то пошло не так 2", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.killJobs()
    }

    private fun showViewWorking() {
        binding.loading.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loading.loadingFrameLayout.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}