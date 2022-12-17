package ru.catdimson.bjjmechanics.viewmodel.actions

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.actions.ActionsInteractor
import ru.catdimson.bjjmechanics.services.actions.ActionsService
import ru.catdimson.bjjmechanics.viewmodel.BaseAndroidViewModel

class ActionsViewModel(
    private val interactor: ActionsInteractor,
    private val actionService: ActionsService,
    application: Application
) : BaseAndroidViewModel<AppState>(application) {

    private val liveDataForViewToObserve: LiveData<AppState> = liveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun onShowActions() {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            showActions()
        }
    }

    private suspend fun showActions() {
        withContext(Dispatchers.IO) {
            val startAction = interactor.findStartingAction()
            val prevActions = interactor.findByNextId(startAction[0].id)
            startAction[0].isStart = prevActions.isEmpty()
            liveData.postValue(AppState.SuccessCurrentAction(startAction))
            val nextActions = interactor.findByPrevId(startAction[0].id)
            liveData.postValue(AppState.SuccessNextActions(nextActions))
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