package ru.catdimson.bjjmechanics.viewmodel.actions

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.actions.ActionsInteractor
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
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

    fun onShowCurrentAction() {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            registration()
        }
    }

    private suspend fun registration(id: Int = 3) {
        withContext(Dispatchers.IO) {
            liveData.postValue(AppState.SuccessCurrentAction(listOf(interactor.findById(id))))
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