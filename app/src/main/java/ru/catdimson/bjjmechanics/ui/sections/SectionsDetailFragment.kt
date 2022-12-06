package ru.catdimson.bjjmechanics.ui.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.api.load
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentSectionsDetailBinding
import ru.catdimson.bjjmechanics.domain.entities.sections.Belt
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.user.Person
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.viewmodel.sections.SectionDetailsViewModel
import java.util.*

const val SECTION_DETAIL_ID = "SECTION_DETAIL_ID"

class SectionsDetailFragment : AbstractScreenFragment<FragmentSectionsDetailBinding>(FragmentSectionsDetailBinding::inflate) {

    private lateinit var viewModel: SectionDetailsViewModel
    override var scope = getKoin().getOrCreateScope("sectionDetailsScope", named("sectionDetailsScope"))
    private val authMap = mapOf(
        Pair("Authorization", "${"Basic"} ${Base64.getEncoder().encodeToString("user:password".toByteArray())}")
    )
    private val adapter by lazy { CoachesAdapter() }

    companion object {
        @JvmStatic
        fun newInstance(sectionId: Int): SectionsDetailFragment {
            val args = Bundle()
            args.putInt(SECTION_DETAIL_ID, sectionId)
            val fragment = SectionsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initIncomingEvents()
        initOutgoingEvents(arguments?.get(SECTION_DETAIL_ID) as Int)

        initFakeAdapterData()
    }

    private fun initViewModel() {
        viewModel = scope.get()
    }

    private fun initOutgoingEvents(id: Int) {
        viewModel.onShowSectionById(id, authMap)
    }

    private fun initIncomingEvents() {
        viewModel.subscribe().observe(requireActivity()) {
            renderData(it)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessSectionDetail -> {
                showViewWorking()
                appState.data.let {
                    if (it == null) {
                        Toast.makeText(context, "Данных нет, сорян", Toast.LENGTH_LONG).show()
                    } else {
                        setDataToScreen(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.loading.apply {
                        progressBarRound.visibility = View.GONE
                    }
                } else {
                    binding.loading.apply {
                        progressBarRound.visibility = View.VISIBLE
                    }
                }
            }
            is AppState.Error -> {
                showViewWorking()
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show()
            }
            else -> {
                showViewWorking()
                Toast.makeText(context, "Что-то пошло не так 2", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showViewWorking() {
        binding.loading.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loading.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun setDataToScreen(section: SectionInfo?) {
        binding.apply {
            title.text = section?.title
            image.load("https://i.pinimg.com/originals/e3/7b/ab/e37bab9df8343b528cc3a90abf507c7b.jpg"/*item.previewUrl*/)
            description.text = section?.fullDescription
            address.text = section?.address?.location
            coachesRecyclerView.adapter = adapter
        }
    }

    private fun initFakeAdapterData() {
        val fakeData = mutableListOf<Coach>()

        for (i in 1..20) {
            fakeData.add(
                Coach(
                    i,
                    "Тренер вырастил много чемпионов",
                    i,
                    Person(
                        i,
                        "Александр",
                        "Андреевич",
                        "Василенко",
                        null,
                        "8920777880" + i
                    ),
                    Belt(
                        i,
                        "Чёрный",
                        0,
                        "http://mjjc.ru/img/vz5.png"
                    ),
                    "https://gbjacksonville.com/wp-content/uploads/2021/06/Rickson-Gracie-768x768.jpg",
                    "https://gbjacksonville.com/wp-content/uploads/2021/06/Rickson-Gracie.jpg"
                )
            )
        }

        adapter.setData(fakeData)
    }

}