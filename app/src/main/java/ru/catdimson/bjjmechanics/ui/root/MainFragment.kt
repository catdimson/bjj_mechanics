package ru.catdimson.bjjmechanics.ui.root

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.databinding.FragmentMainBinding
import ru.catdimson.bjjmechanics.ui.BaseFragment
import ru.catdimson.bjjmechanics.ui.actions.ActionsFragment
import ru.catdimson.bjjmechanics.ui.auth.AuthFragment
import ru.catdimson.bjjmechanics.ui.sections.SectionsFragment
import ru.catdimson.bjjmechanics.ui.terms.TermsFragment

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
                .replace(R.id.container, ActionsFragment.newInstance())
                .commit()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigationMenu()
    }

    private fun initBottomNavigationMenu() {
        binding.menuBottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_item_education -> {
                    navigationTo(ActionsFragment.newInstance())
                    true
                }
                R.id.menu_item_auth -> {
                    navigationTo(AuthFragment.newInstance())
                    true
                }
                R.id.menu_item_terms -> {
                    navigationTo(TermsFragment.newInstance())
                    true
                }
                R.id.menu_item_sections -> {
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