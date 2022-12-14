package ru.catdimson.bjjmechanics.viewmodel.auth

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.catdimson.bjjmechanics.App
import ru.catdimson.bjjmechanics.services.auth.AuthorizationService
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractor
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.viewmodel.BaseAndroidViewModel
import java.lang.RuntimeException
import java.util.*

class AuthViewModel(
    private val interactor: AuthInteractor,
    private val authService: AuthorizationService,
    application: Application
) : BaseAndroidViewModel<AppState>(application) {

    private val patternUserId = """.*\/(?<userId>\d+)$""".toRegex()
    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onAuthStartState() {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            authStartState()
        }
    }

    fun onLogoutState() {
        liveData.value = AppState.SuccessLogoutState(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            logoutState()
        }
    }

    fun onRegistrationStartState() {
        liveData.postValue(AppState.SuccessRegistrationState(null))
    }

    fun onRegistration(regData: RegistrationData) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            registration(regData)
        }
    }

    fun onLogin(regData: RegistrationData) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            login(regData)
        }
    }

    fun onLogout() {
        authService.clearSharedPreferences(getApplication<App>())
        viewModelCoroutineScope.launch {
            authStartState()
        }
    }

    fun onRefreshToken(accessToken: String, refreshToken: String) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            refresh(accessToken, JwtRefreshRequest(refreshToken))
        }
    }

    private suspend fun registration(regData: RegistrationData) {
        withContext(Dispatchers.IO) {
            val response = interactor.registration(regData)
            val userId = extractUserId(response)
            if (response.isSuccessful && userId != null) {
                val jwtRequest = JwtRequest(
                    regData.login,
                    regData.password
                )
                val jwtResponse = interactor.login(jwtRequest)
                authService.saveTokensToSharedPref(jwtResponse, getApplication())
                authService.saveCurrentUserId(userId, getApplication())
            } else {
                liveData.postValue(AppState.Error(RuntimeException("Неизвестная ошибка. Попробуйте позже")))
            }
            liveData.postValue(AppState.SuccessRegistration(response))
        }
    }

    private fun extractUserId(response: Response<Void>): Int? {
        val location = response.headers().get("Location")

        if (location != null) {
            val matcher = patternUserId.matchEntire(location)

            if (matcher != null) {
                val groups = matcher.groups as? MatchNamedGroupCollection

                if (groups != null) {
                    return groups["userId"]?.value?.toInt()
                }
                return null
            }
            return null
        }
        return null
    }

    private suspend fun refresh(accessToken: String, jwtRefresh: JwtRefreshRequest) {
        val authorization = mapOf(
            Pair("Authorization", "${"Bearer "} ${accessToken}")
        )
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessRefreshToken(interactor.refresh(jwtRefresh, authorization)))
        }
    }

    private suspend fun logoutState() {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessLogoutState(null))
        }
    }

    private suspend fun authStartState() {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessAuthStartingState(null))
        }
    }

    private suspend fun login(regData: RegistrationData) {
        val jwtRequest = JwtRequest(
            regData.login,
            regData.password
        )
        val jwtResponse = interactor.login(jwtRequest)
        authService.saveTokensToSharedPref(jwtResponse, getApplication())
        liveData.postValue(AppState.SuccessLogoutState(null))
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.value = null
        super.onCleared()
    }
}