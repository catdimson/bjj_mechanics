package ru.catdimson.bjjmechanics.data

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

sealed class AppState {

    data class SuccessLogoutState(val data: Boolean?) : AppState()

    data class SuccessLogin(val data: JwtResponse) : AppState()

    data class SuccessRegistration(val data: Response<Void>) : AppState()

    data class SuccessRegistrationState(val data: Boolean?) : AppState()

    data class SuccessAuthStartingState(val data: Boolean?) : AppState()

    data class SuccessRefreshToken(val data: JwtResponse) : AppState()

    data class SuccessSections(val data: List<SectionInfo>) : AppState()

    data class SuccessSectionDetail(val data: SectionInfo?) : AppState()

    data class SuccessTerms(val data: List<Term>) : AppState()

    data class SuccessTermDetail(val data: Term?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()

}