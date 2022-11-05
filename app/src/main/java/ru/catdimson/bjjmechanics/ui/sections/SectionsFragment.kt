package ru.catdimson.bjjmechanics.ui.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.R
import ru.catdimson.bjjmechanics.databinding.FragmentSectionsBinding
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.ui.BaseFragment
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionsViewModel

class SectionsFragment : BaseFragment<FragmentSectionsBinding>(FragmentSectionsBinding::inflate) {

    private lateinit var viewModel: SectionsViewModel
    private val scope by lazy { getKoin().getOrCreateScope("sectionsScope", named("sectionsScope")) }
    private val adapter by lazy { SectionsAdapter(onListItemClickListener) }

    private val onListItemClickListener: SectionsAdapter.OnListItemClickListener =
        object : SectionsAdapter.OnListItemClickListener {
            override fun onItemClick(data: SectionInfo) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SectionsDetailFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
        }

    companion object {
        @JvmStatic
        fun newInstance() = SectionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}