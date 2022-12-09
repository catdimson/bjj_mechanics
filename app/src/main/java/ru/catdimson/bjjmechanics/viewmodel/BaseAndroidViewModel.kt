package ru.catdimson.bjjmechanics.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.catdimson.bjjmechanics.data.AppState

abstract class BaseAndroidViewModel<T : AppState>(
    application: Application
) : AndroidViewModel(application) {

    protected open val liveData: MutableLiveData<T> = MutableLiveData()

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun handleError(error: Throwable)
}