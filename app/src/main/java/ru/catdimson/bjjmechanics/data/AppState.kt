package ru.catdimson.bjjmechanics.data

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

sealed class AppState {

    data class SuccessSections(val data: List<SectionInfo>?) : AppState()

    data class SuccessTerms(val data: List<Term>) : AppState()

    data class SuccessTermDetail(val data: Term?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()

}