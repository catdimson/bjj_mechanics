package ru.catdimson.bjjmechanics.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import org.koin.core.scope.Scope
import ru.catdimson.bjjmechanics.data.AppState

abstract class AbstractScreenFragment<T : ViewBinding> (
    inflate: (inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) -> T
) : BaseFragment<T>(inflate) {

    abstract var scope: Scope

    abstract fun renderData(appState: AppState)

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }
}