package ru.catdimson.bjjmechanics.viewmodel.sections

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.sections.SectionsInteractor
import ru.catdimson.bjjmechanics.viewmodel.BaseViewModel

class SectionsViewModel(
    private val interactor: SectionsInteractor
): BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onShowSectionsByCity(city: String, authMap: Map<String, String>) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getSectionsByCity(city, authMap)
        }
    }

    fun killJobs() {
        viewModelCoroutineScope.cancel()
    }

    private suspend fun getSectionsByCity(city: String, authMap: Map<String, String>) {
        withContext(Dispatchers.Main) {
            liveData.postValue(AppState.SuccessSections(interactor.findByCity(city, authMap)))
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