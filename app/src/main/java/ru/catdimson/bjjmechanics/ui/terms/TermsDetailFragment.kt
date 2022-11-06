package ru.catdimson.bjjmechanics.ui.terms

import ru.catdimson.bjjmechanics.databinding.FragmentTermsDetailBinding
import ru.catdimson.bjjmechanics.ui.BaseFragment

class TermsDetailFragment : BaseFragment<FragmentTermsDetailBinding>(FragmentTermsDetailBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = TermsDetailFragment()
    }

}