package ru.catdimson.bjjmechanics.ui.sections

import ru.catdimson.bjjmechanics.databinding.FragmentMainBinding
import ru.catdimson.bjjmechanics.ui.BaseFragment

class SectionsFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = SectionsFragment()
    }

}