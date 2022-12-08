package ru.catdimson.bjjmechanics.viewmodel.auth

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.App
import ru.catdimson.bjjmechanics.core.auth.AuthorizationService
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractor
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.viewmodel.BaseAndroidViewModel
import ru.catdimson.bjjmechanics.viewmodel.BaseViewModel

class AuthViewModel(
    private val interactor: AuthInteractor,
    private val authService: AuthorizationService,
    application: Application
) : BaseAndroidViewModel<AppState>(application) {

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

    fun onRegistrationStartState() {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            registrationStartState()
        }
    }

    fun onLogout() {
        authService.removeTokens(getApplication<App>())
    }

    fun onRefreshToken(refreshToken: String) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            refresh(JwtRefreshRequest(refreshToken))
        }
    }

    private suspend fun refresh(jwtRefresh: JwtRefreshRequest) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessRefreshToken(interactor.refresh(jwtRefresh)))
        }
    }

    private suspend fun authStartState() {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessAuthStartingState(null))
        }
    }

    private suspend fun registrationStartState() {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessRegistrationState(null))
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.value = null
        super.onCleared()
    }
}