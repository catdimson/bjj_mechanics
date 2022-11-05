package ru.catdimson.bjjmechanics.ui.sections

import ru.catdimson.bjjmechanics.databinding.FragmentSectionsDetailBinding
import ru.catdimson.bjjmechanics.ui.BaseFragment

class SectionsDetailFragment : BaseFragment<FragmentSectionsDetailBinding>(FragmentSectionsDetailBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = SectionsDetailFragment()
    }

}