package ru.catdimson.bjjmechanics.ui.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.databinding.FragmentTermsBinding
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.ui.BaseFragment

class TermsFragment : BaseFragment<FragmentTermsBinding>(FragmentTermsBinding::inflate) {

    private val onListItemClickListener: TermsAdapter.OnListItemClickListener =
        object : TermsAdapter.OnListItemClickListener {
            override fun onItemClick(data: Term) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, TermsDetailFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = TermsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

}