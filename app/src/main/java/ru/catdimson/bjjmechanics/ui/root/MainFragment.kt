package ru.catdimson.bjjmechanics.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.databinding.FragmentMainBinding
import ru.catdimson.bjjmechanics.ui.BaseFragment
import ru.catdimson.bjjmechanics.ui.sections.SectionsFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        if (savedInstanceState == null) {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SectionsFragment.newInstance())
                .commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigationMenu()
    }

    private fun initBottomNavigationMenu() {
        binding.menuBnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item1 -> {
                    navigationTo(SectionsFragment.newInstance())
                    true
                }
                R.id.menu_item2 -> {
                    navigationTo(SectionsFragment.newInstance())
                    true
                }
                R.id.menu_item3 -> {
                    navigationTo(SectionsFragment.newInstance())
                    true
                }
                R.id.menu_item4 -> {
                    navigationTo(SectionsFragment.newInstance())
                    true
                }
                R.id.menu_item5 -> {
                    navigationTo(SectionsFragment.newInstance())
                    true
                }
                else -> true
            }
        }
    }

    private fun navigationTo(f: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, f)
            .addToBackStack(null)
            .commit()
    }
}