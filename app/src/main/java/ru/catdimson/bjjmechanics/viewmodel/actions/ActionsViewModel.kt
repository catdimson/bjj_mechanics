package ru.catdimson.bjjmechanics.viewmodel.actions

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.domain.datasource.interactor.actions.ActionsInteractor
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.domain.entities.actions.chain.ActionsStack
import ru.catdimson.bjjmechanics.domain.services.actions.ActionsService
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

    fun onReset() {
        actionService.clearSharedPreferences(getApplication())
    }

    fun onPrevAction() {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            prevAction()
        }
    }

    fun onNextAction(data: Action) {
        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            nextAction(data)
        }
    }

    private suspend fun showActions() {
        withContext(Dispatchers.IO) {
            var actionsStack = actionService.getActionsChainFromSharedPref<Int>(getApplication())
            if (actionsStack?.hasMore() == true) {
                val currentAction = interactor.findById(actionsStack.peek()!!)
                val prevActions = interactor.findByNextId(currentAction.id)
                currentAction.isStart = prevActions.isEmpty()
                liveData.postValue(AppState.SuccessCurrentAction(mutableListOf(currentAction)))
                val nextActions = interactor.findByPrevId(currentAction.id)
                if (nextActions.isEmpty()) {
                    // тогда окончание схватки
                } else {
                    liveData.postValue(AppState.SuccessNextActions(nextActions))
                }
            } else {
                val startAction = interactor.findStartingAction()
                val prevActions = interactor.findByNextId(startAction[0].id)
                startAction[0].isStart = prevActions.isEmpty()
                liveData.postValue(AppState.SuccessCurrentAction(startAction))
                val nextActions = interactor.findByPrevId(startAction[0].id)
                liveData.postValue(AppState.SuccessNextActions(nextActions))
                actionsStack = ActionsStack()
                actionsStack.push(startAction[0].id)
                actionService.saveActionsChainToSharedPref(actionsStack, getApplication())
            }
        }
    }

    private suspend fun prevAction() {
        withContext(Dispatchers.IO) {
            val actionsStack = actionService.getActionsChainFromSharedPref<Int>(getApplication())
            if (actionsStack != null && actionsStack.size() > 0) {
                actionsStack.pop()!!
                val newCurrentIdAction = actionsStack.peek()!!
                val currentAction = interactor.findById(newCurrentIdAction)
                val prevActions = interactor.findByNextId(newCurrentIdAction)
                currentAction.isStart = prevActions.isEmpty()
                liveData.postValue(AppState.SuccessCurrentAction(mutableListOf(currentAction)))
                val nextActions = interactor.findByPrevId(currentAction.id)
                if (nextActions.isNotEmpty()) {
                    liveData.postValue(AppState.SuccessNextActions(nextActions))
                }
                actionService.saveActionsChainToSharedPref(actionsStack, getApplication())
            }
        }
    }

    private suspend fun nextAction(data: Action) {
        withContext(Dispatchers.IO) {
            var actionsStack = actionService.getActionsChainFromSharedPref<Int>(getApplication())
            val currentAction = interactor.findById(data.id)
            liveData.postValue(AppState.SuccessCurrentAction(mutableListOf(currentAction)))
            val nextActions = interactor.findByPrevId(data.id)
            if (nextActions.isNotEmpty()) {
                liveData.postValue(AppState.SuccessNextActions(nextActions))
            } else {
                liveData.postValue(AppState.SuccessActionsFinish(true))
            }
            if (actionsStack == null) {
                actionsStack = ActionsStack()
            }
            actionsStack.push(data.id)
            actionService.saveActionsChainToSharedPref(actionsStack, getApplication())

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