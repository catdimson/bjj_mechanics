package ru.catdimson.bjjmechanics.data

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

sealed class AppState {

    data class SuccessSections(val data: List<SectionInfo>?) : AppState()

    data class Error(val error: Throwable) : AppState()

    data class Loading(val progress: Int?) : AppState()

}