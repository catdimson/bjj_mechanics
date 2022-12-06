package ru.catdimson.bjjmechanics.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentAuthBinding
import ru.catdimson.bjjmechanics.databinding.FragmentSectionsBinding
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.ui.terms.TermsFragment
import ru.catdimson.bjjmechanics.viewmodel.auth.AuthViewModel
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionsViewModel

class AuthFragment : AbstractScreenFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate)  {

    private lateinit var viewModel: AuthViewModel
    override var scope = getKoin().getOrCreateScope("authScope", named("authScope"))

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

}