package ru.catdimson.bjjmechanics.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.services.auth.AuthorizationService
import ru.catdimson.bjjmechanics.core.validations.LoginValidation
import ru.catdimson.bjjmechanics.core.validations.PasswordValidation
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentAuthBinding
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.services.auth.AuthorizationServiceImpl
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.viewmodel.auth.AuthViewModel

class AuthFragment : AbstractScreenFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private lateinit var viewModel: AuthViewModel
    private lateinit var authService: AuthorizationService
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = scope.get()
        authService = scope.get()

        initIncomingEvents()
        initOutgoingEvents()
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
            viewModel.onLogoutState()
        }

        initLoginEvents()
        initRegistrationEvents()
        initLogoutEvents()
    }

    private fun initLoginEvents() {
        // обрабатываем нажатие кнопки "Не зарегистрированы?"
        binding.registrationLink.setOnClickListener {
            viewModel.onRegistrationStartState()
        }

        // обрабатываем нажатие кнопки "Войти"
        binding.btnInput.setOnClickListener {
            if (validateLogin(binding.login) && validatePassword(binding.password)) {
                viewModel.onLogin(
                    RegistrationData(
                        binding.login.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    )
                )
            }
        }
    }

    private fun initRegistrationEvents() {
        // обрабатываем нажатие кнопки "Зарегистрироваться"
        binding.btnRegistration.setOnClickListener {
            if (validateRegistrationData()) {
                val regData = RegistrationData(
                    binding.registrationLogin.editText?.text.toString(),
                    binding.registrationPassword.editText?.text.toString()
                )
                viewModel.onRegistration(regData)
            }
        }
    }

    private fun initLogoutEvents() {
        binding.btnLogout.setOnClickListener {
            viewModel.onLogout()
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessLogoutState -> {
                showLogoutView()
                showViewWorking()
            }
            is AppState.SuccessRegistration -> {
                showLogoutView()
                showViewWorking()
            }
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
            loginGroup.visibility = View.GONE
            registrationGroup.visibility = View.GONE
            logoutGroup.visibility = View.VISIBLE
        }
    }

    private fun showLoginView() {
        binding.apply {
            logoutGroup.visibility = View.GONE
            registrationGroup.visibility = View.GONE
            loginGroup.visibility = View.VISIBLE
        }
    }

    private fun saveTokens(jwtResponse: JwtResponse) {
        authService.saveTokensToSharedPref(jwtResponse, requireContext())
    }

    private fun validateLogin(loginField: TextInputLayout): Boolean {
        val loginValidation = LoginValidation(loginField.editText?.text.toString())

        return if (!loginValidation.isValid()) {
            loginField.error =
                "Логин должен состоять из цифр, букв и знаков _ в количестве от 4 до 32"
            false
        } else {
            loginField.error = null
            loginField.isErrorEnabled = false
            true
        }
    }

    private fun validatePassword(passwordField: TextInputLayout): Boolean {
        val passwordValidation = PasswordValidation(passwordField.editText?.text.toString())


        return if (!passwordValidation.isValid()) {
            passwordField.error =
                "Пароль должен состоять из цифр, букв и знаков _ в количестве от 8 до 32"
            false
        } else {
            passwordField.error = null
            passwordField.isErrorEnabled = false
            true
        }
    }

    private fun validateEmail(): Boolean {
        return true
    }

    private fun validatePhone(): Boolean {
        return true
    }

    private fun validateRegistrationData(): Boolean {
        return validateLogin(binding.registrationLogin) && validatePassword(binding.registrationPassword) && validateEmail() && validatePhone()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

}