package ru.catdimson.bjjmechanics.viewmodel.terms

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.core.auth.AuthorizationService
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractor
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.entities.user.User
import ru.catdimson.bjjmechanics.dto.terms.CommentDto
import ru.catdimson.bjjmechanics.viewmodel.BaseAndroidViewModel

class TermDetailsViewModel(
    private val termsInteractor: TermsInteractor,
    private val authInteractor: AuthInteractor,
    private val authService: AuthorizationService,
    application: Application
) : BaseAndroidViewModel<AppState>(application) {

    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onShowTermById(id: Int, authMap: Map<String, String>) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getTermById(id, authMap)
        }
    }

    private suspend fun getTermById(id: Int, authMap: Map<String, String>) {
        withContext(Dispatchers.IO) {
            if (isContainsTokens()) {
                val jwtResponse = authInteractor.refresh(
                    JwtRefreshRequest(getRefreshToken()!!)
                )
                authService.saveTokensToSharedPref(jwtResponse, getApplication())
                liveData.postValue(AppState.SuccessTermDetailWithAccess(termsInteractor.findById(id, authMap)))
            } else {
                liveData.postValue(AppState.SuccessTermDetail(termsInteractor.findById(id, authMap)))
            }
        }
    }

    fun sendComment(commentText: String, termId: Int) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            saveComment(commentText, termId)
        }
    }

    private suspend fun saveComment(commentText: String, termId: Int) {
        withContext(Dispatchers.IO) {
            val userId = authService.getUserId(getApplication())
//            val authMap = mutableMapOf<String, String>().put(
//                "Auth"
//            )
            if (userId != null) {
                val commentDto = CommentDto(commentText, userId, termId)
//                termsInteractor.saveTermComment(commentDto, authMap)
//                liveData.postValue(AppState.SuccessTermDetailSendComment())
            } else {
                liveData.postValue(AppState.Error(IllegalArgumentException("Что-то пошло не так")))
            }
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.value = null
        super.onCleared()
    }

    private fun isContainsTokens(): Boolean {
        return getRefreshToken() != null && getAccessToken() != null
    }

    private fun getRefreshToken(): String? {
        return authService.getRefreshTokenFromSharedPref(getApplication())
    }

    private fun getAccessToken(): String? {
        return authService.getAccessTokenFromSharedPref(getApplication())
    }
}