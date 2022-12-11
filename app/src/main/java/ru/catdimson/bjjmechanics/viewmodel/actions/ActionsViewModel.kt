package ru.catdimson.bjjmechanics.viewmodel.actions

import android.app.Application
import androidx.lifecycle.LiveData
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

    override fun handleError(error: Throwable) {
        TODO("Not yet implemented")
    }

}