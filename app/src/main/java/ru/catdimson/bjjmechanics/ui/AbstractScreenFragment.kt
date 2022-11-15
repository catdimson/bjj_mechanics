package ru.catdimson.bjjmechanics.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ru.catdimson.bjjmechanics.data.AppState

abstract class AbstractScreenFragment<T : ViewBinding> (
    inflate: (inflater: LayoutInflater, parent: ViewGroup?, attachToRoot: Boolean) -> T
) : BaseFragment<T>(inflate) {

    abstract fun renderData(appState: AppState)

}