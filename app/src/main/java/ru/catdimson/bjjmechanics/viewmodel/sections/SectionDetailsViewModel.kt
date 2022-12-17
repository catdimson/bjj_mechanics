package ru.catdimson.bjjmechanics.viewmodel.sections

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.viewmodel.BaseViewModel

class SectionDetailsViewModel(
    private val interactor: SectionsInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onShowSectionById(id: Int, authMap: Map<String, String>) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getSectionById(id, authMap)
        }
    }

    private suspend fun getSectionById(id: Int, authMap: Map<String, String>) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessSectionDetail(interactor.findById(id, authMap)))
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