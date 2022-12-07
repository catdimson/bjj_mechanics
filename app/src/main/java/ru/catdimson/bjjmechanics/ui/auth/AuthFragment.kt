package ru.catdimson.bjjmechanics.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.core.auth.AuthorizationService
import ru.catdimson.bjjmechanics.core.auth.AuthorizationServiceImpl
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentAuthBinding
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.viewmodel.auth.AuthViewModel

class AuthFragment : AbstractScreenFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate)  {

    private lateinit var viewModel: AuthViewModel
    private lateinit var authService: AuthorizationService
    override var scope = getKoin().getOrCreateScope("authScope", named("authScope"))

    companion object {
        @JvmStatic
        fun newInstance() = AuthFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authService = AuthorizationServiceImpl()
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
        initIncomingEvents()
        initOutgoingEvents()
    }

    private fun initViewModel() {
        viewModel = scope.get()
    }

    private fun initIncomingEvents() {
        viewModel.subscribe().observe(requireActivity()) {
            renderData(it)
        }
    }

    private fun initOutgoingEvents() {
        val accessToken = getAccessTokenFromSP()
        val refreshToken = getRefreshTokenFromSP()
        if (accessToken == null || refreshToken == null) {
            viewModel.onAuthStartState()
        } else {
            viewModel.onRefreshToken(refreshToken)
        }

    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessAuthStartingState -> {
                showLoginView()
                showViewWorking()
            }
            is AppState.SuccessRefreshToken -> {
                saveTokens(appState.data)
                showLogoutView()
                showViewWorking()
            }
            is AppState.SuccessRegistrationState -> {
                showRegistrationView()
                showViewWorking()
            }
            is AppState.SuccessLogin -> {
                saveTokens(appState.data)
                showLogoutView()
                showViewWorking()
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

    private fun getAccessTokenFromSP(): String? {
        return context?.let { authService.getAccessTokenFromSharedPref(it) }
    }

    private fun getRefreshTokenFromSP(): String? {
        return context?.let { authService.getRefreshTokenFromSharedPref(it) }
    }

    private fun showViewWorking() {
        binding.loading.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loading.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun showRegistrationView() {
        binding.apply {
            logoutGroup.visibility = View.GONE
            loginGroup.visibility = View.GONE
            registrationGroup.visibility = View.VISIBLE
        }
    }

    private fun showLogoutView() {
        binding.apply {
            logoutGroup.visibility = View.VISIBLE
            loginGroup.visibility = View.GONE
            registrationGroup.visibility = View.GONE
        }
    }

    private fun showLoginView() {
        binding.apply {
            logoutGroup.visibility = View.GONE
            loginGroup.visibility = View.VISIBLE
            registrationGroup.visibility = View.GONE
        }
    }

    private fun saveTokens(jwtResponse: JwtResponse) {
        authService.saveTokensToSharedPref(jwtResponse, requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

}