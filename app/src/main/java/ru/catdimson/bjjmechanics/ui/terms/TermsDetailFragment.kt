package ru.catdimson.bjjmechanics.ui.terms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import ru.catdimson.bjjmechanics.data.AppState
import ru.catdimson.bjjmechanics.databinding.FragmentTermsDetailBinding
import ru.catdimson.bjjmechanics.domain.entities.system.Person
import ru.catdimson.bjjmechanics.domain.entities.system.Role
import ru.catdimson.bjjmechanics.domain.entities.system.User
import ru.catdimson.bjjmechanics.domain.entities.terms.Comment
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.ui.AbstractScreenFragment
import ru.catdimson.bjjmechanics.ui.BaseFragment
import ru.catdimson.bjjmechanics.viewmodel.terms.TermDetailsViewModel
import ru.catdimson.bjjmechanics.viewmodel.terms.TermsViewModel
import java.time.LocalDate
import java.util.*
import java.util.regex.Pattern

const val TERM_DETAIL_ID = "TERM_DETAIL_ID"

class TermsDetailFragment : AbstractScreenFragment<FragmentTermsDetailBinding>(FragmentTermsDetailBinding::inflate) {

    private lateinit var viewModel: TermDetailsViewModel
    override var scope = getKoin().getOrCreateScope("termDetailsScope", named("termDetailsScope"))
    private val authMap = mapOf(
        Pair("Authorization", "${"Basic"} ${Base64.getEncoder().encodeToString("user:password".toByteArray())}")
    )
    private val adapter by lazy { CommentAdapter() }

    companion object {
        @JvmStatic
        fun newInstance(termId: Int): TermsDetailFragment {
            val args = Bundle()
            args.putInt(TERM_DETAIL_ID, termId)
            val fragment = TermsDetailFragment()
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
        initOutgoingEvents(arguments?.get(TERM_DETAIL_ID) as Int)

        initFakeAdapter()
    }

    private fun initViewModel() {
        viewModel = scope.get()
    }

    private fun initOutgoingEvents(id: Int) {
        viewModel.onShowTermById(id, authMap)
    }

    private fun initIncomingEvents() {
        viewModel.subscribe().observe(requireActivity()) {
            renderData(it)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessTermDetail -> {
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
                        progressBarHorizontal.visibility = View.VISIBLE
                        progressBarRound.visibility = View.GONE
                        progressBarHorizontal.progress = appState.progress
                    }
                } else {
                    binding.loading.apply {
                        progressBarHorizontal.visibility = View.GONE
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

    private fun setDataToScreen(term: Term?) {
        binding.apply {
            name.text = term?.name
            termType.text = term?.termType?.title
            description.text = term?.description
            commentsRecyclerView.adapter = adapter
        }
        showVideo(extractYoutubeId(term?.video?.url!!))
    }

    private fun initFakeAdapter() {
        val fakeData = mutableListOf<Comment>()

        for (i in 1..50) {
            fakeData.add(
                Comment(
                    i,
                    "Очень содержательный комментарий " + i,
                    User(i,
                        "user" + i,
                        "pass" + i,
                        Role(
                            1,
                            "Пользователь",
                            "Рядовой юзер"
                        ),
                        true,
                        Person(
                            i,
                            "Иван " + i,
                            "Иванович " + i,
                            "Иванов " + i,
                            LocalDate.now(),
                            "+7920111223" + i
                        )
                    ),
                    LocalDate.now()
                )
            )
        }

        adapter.setData(fakeData)
    }

    private fun showVideo(videoId: String) {
        with(binding) {
            lifecycle.addObserver(binding.video)

            video.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        }
        binding.video.visibility = View.VISIBLE
    }

    fun extractYoutubeId(ytUrl: String): String {
        var vId: String? = null
        val pattern = Pattern.compile(
            "(?:https?:\\/\\/)?(?:www\\.)?youtu\\.?be(?:\\.com)?\\/?.*(?:watch|embed)?(?:.*v=|v\\/|\\/)([\\w\\-_]+)\\&?"
        )
        val matcher = pattern.matcher(ytUrl)
        if (matcher.matches()) {
            vId = matcher.group(1)
        }
        return vId!!
    }
}