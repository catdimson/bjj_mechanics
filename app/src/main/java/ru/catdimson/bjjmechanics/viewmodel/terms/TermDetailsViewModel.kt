package ru.catdimson.bjjmechanics.viewmodel.terms

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.auth.AuthInteractor
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractor
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.terms.Comment
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.entities.user.User
import ru.catdimson.bjjmechanics.domain.services.auth.AuthorizationService
import ru.catdimson.bjjmechanics.dto.terms.CommentDto
import ru.catdimson.bjjmechanics.utils.extractIdFromHeaderLocation
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

    fun onShowTermById(id: Int) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getTermById(id)
        }
    }

    private suspend fun getTermById(id: Int) {
        withContext(Dispatchers.IO) {
            if (isContainsTokens()) {
                try {
                    val authorization = mapOf(
                        Pair("Authorization", "${"Bearer "} ${getAccessToken()}")
                    )
                    val jwtResponse = authInteractor.refresh(
                        JwtRefreshRequest(getRefreshToken()!!),
                        authorization
                    )
                    authService.saveTokensToSharedPref(jwtResponse, getApplication())
                    liveData.postValue(
                        AppState.SuccessTermDetailWithAccess(
                            termsInteractor.findById(id)
                        )
                    )
                } catch (e: HttpException) {
                    authService.clearSharedPreferences(getApplication())
                    liveData.postValue(AppState.SuccessTermDetail(termsInteractor.findById(id)))
                }
            } else {
                liveData.postValue(AppState.SuccessTermDetail(termsInteractor.findById(id)))
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
            val user = authService.getUserInfo(getApplication())
            val authorization = mapOf(
                Pair("Authorization", "${"Bearer "} ${getAccessToken()}")
            )
            if (user.id != null) {
                val user = User(user.id, user.login, null, null, null, null)
                val term = Term(termId, null, null, null, null, null)
                val commentDto = CommentDto(commentText, user, term)
                val response = termsInteractor.saveTermComment(commentDto, authorization)
                val commentId = extractIdFromHeaderLocation(response)
                val comment = Comment(
                    commentId,
                    commentText,
                    user,
                    term
                )
                liveData.postValue(AppState.SuccessTermDetailSendComment(comment))
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