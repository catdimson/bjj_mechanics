package ru.catdimson.bjjmechanics.viewmodel.sections

import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.viewmodel.BaseViewModel

class SectionDetailsViewModel(
    private val interactor: SectionsInteractor
): BaseViewModel<AppState>() {
    override fun getData(word: String, isOnline: Boolean) {
        TODO("Not yet implemented")
    }

    override fun handleError(error: Throwable) {
        TODO("Not yet implemented")
    }

}