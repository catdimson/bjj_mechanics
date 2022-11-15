package ru.catdimson.bjjmechanics.viewmodel.terms

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.terms.TermsInteractor
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.viewmodel.BaseViewModel

class TermsViewModel(
    private val interactor: TermsInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onShowAllTerms(authMap: Map<String, String>) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getAllTerms(authMap)
        }
    }

    fun onShowTermById(id: Int) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            getTermById(id)
        }
    }

    private suspend fun getAllTerms(authMap: Map<String, String>) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessTerms(interactor.findAll(authMap)))
        }
    }

    private suspend fun getTermById(id: Int) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessTerm(interactor.findById(id)))
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