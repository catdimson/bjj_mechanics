package ru.catdimson.bjjmechanics.ui.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentActionsBinding
import ru.catdimson.bjjmechanics.databinding.FragmentAuthBinding
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.ui.actions.current.CurrentActionAdapter
import ru.catdimson.bjjmechanics.ui.actions.next.NextActionAdapter
import ru.catdimson.bjjmechanics.ui.terms.TermsAdapter
import ru.catdimson.bjjmechanics.ui.terms.TermsDetailFragment
import ru.catdimson.bjjmechanics.viewmodel.actions.ActionsViewModel

class ActionsFragment : AbstractScreenFragment<FragmentActionsBinding>(FragmentActionsBinding::inflate) {

    private lateinit var viewModel: ActionsViewModel
    override var scope = getKoin().getOrCreateScope("actionsScope", named("actionsScope"))
    private val adapterCurrentAction by lazy { CurrentActionAdapter(onListItemClickListenerToPrev, onListItemButtonClickListener) }
    private val adapterNextActions by lazy { NextActionAdapter(onListItemClickListenerToNext, onListItemButtonClickListener) }

    private val onListItemClickListenerToPrev: CurrentActionAdapter.OnListItemClickListener =
        object : CurrentActionAdapter.OnListItemClickListener {
            override fun onItemClick(data: Action) {
                Toast.makeText(context, "Кликнул на предыдущий приём", Toast.LENGTH_SHORT).show()
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.container, TermsDetailFragment.newInstance(data.id))
//                    .addToBackStack(null)
//                    .commit()
            }
        }

    private val onListItemClickListenerToNext: NextActionAdapter.OnListItemClickListener =
        object : NextActionAdapter.OnListItemClickListener {
            override fun onItemClick(data: Action) {
                Toast.makeText(context, "Кликнул на следующий приём", Toast.LENGTH_SHORT).show()
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.container, TermsDetailFragment.newInstance(data.id))
//                    .addToBackStack(null)
//                    .commit()
            }
        }

    private val onListItemButtonClickListener: OnListItemButtonClickListener =
        object : OnListItemButtonClickListener {
            override fun onVideoButtonClick(termId: Int?) {
                val id = termId ?: 1
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, TermsDetailFragment.newInstance(id))
                    .addToBackStack(null)
                    .commit()
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = ActionsFragment()
    }

    private fun setDataToAdapterCurrentAction(data: List<Action>) {
        adapterCurrentAction.setData(data)
    }

    private fun setDataToAdapterNextActions(data: List<Action>) {
        adapterNextActions.setData(data)
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
        if (binding.currentAction.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        viewModel = scope.get()
    }

    private fun initOutgoingEvents() {
        viewModel.onShowActions()
    }

    private fun initIncomingEvents() {
        viewModel.subscribe().observe(requireActivity()) {
            renderData(it)
        }
    }

    private fun initRecyclerParts() {
        binding.currentAction.adapter = adapterCurrentAction
        binding.nextActions.adapter = adapterNextActions
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessCurrentAction -> {
                showViewWorking()
                appState.data.let {
                    if (it.isEmpty()) {
                        Toast.makeText(context, "Данных нет, сорян", Toast.LENGTH_LONG).show()
                    } else {
                        setDataToAdapterCurrentAction(it)
                    }
                }
            }
            is AppState.SuccessNextActions -> {
                showViewWorking()
                appState.data.let {
                    if (it.isEmpty()) {
                        Toast.makeText(context, "Данных нет, сорян", Toast.LENGTH_LONG).show()
                    } else {
                        setDataToAdapterNextActions(it)
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