package ru.catdimson.bjjmechanics.ui.actions

import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentAuthBinding
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.viewmodel.actions.ActionsViewModel

class ActionsFragment : AbstractScreenFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private lateinit var viewModel: ActionsViewModel
    override var scope = getKoin().getOrCreateScope("actionsScope", named("actionsScope"))

    companion object {
        @JvmStatic
        fun newInstance() = ActionsFragment()
    }

    override fun renderData(appState: AppState) {
        TODO("Not yet implemented")
    }

}