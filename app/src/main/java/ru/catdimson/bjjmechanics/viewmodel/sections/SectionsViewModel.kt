package ru.catdimson.bjjmechanics.viewmodel.sections

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
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

    override fun getData(word: String, isOnline: Boolean) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(
        word: String = "Белгород",
        isOnline: Boolean = true
    ) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessSections(interactor.findByCity(word)))
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveData.value = AppState.SuccessSections(null)
        super.onCleared()
    }
}