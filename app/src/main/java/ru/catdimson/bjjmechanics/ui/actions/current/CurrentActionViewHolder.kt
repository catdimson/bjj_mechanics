package ru.catdimson.bjjmechanics.ui.actions.current

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.catdimson.bjjmechanics.databinding.ItemActionCurrentBinding
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.ui.actions.OnListItemButtonClickListener

class CurrentActionViewHolder(
    private val binding: ItemActionCurrentBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CurrentActionViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CurrentActionViewHolder(
                ItemActionCurrentBinding.inflate(inflater, parent, false)
            )
        }
    }

    fun bind(
        item: Action,
        onItemClickListener: CurrentActionAdapter.OnListItemClickListener,
        onListItemButtonClickListener: OnListItemButtonClickListener
    ) {
        binding.apply {
//            showVideo(extractYoutubeId(item.video.url))
            if (item.imageUrl == null) {
                currentActionImage.load("https://armlock.com/wp-content/uploads/2015/12/lodi-brazilian-jiu-jitsu.jpg")
            } else {
                currentActionImage.load(item.imageUrl)
            }
            actionTitle.text = item.title
            btnActionPrev.isEnabled = !item.isStart
            btnVideoDetails.setOnClickListener {
                onListItemButtonClickListener.onVideoButtonClick(item.video.terms?.get(0)?.id)
            }
            btnReadDetails.setOnClickListener {
                actionDescription.isVisible = !actionDescription.isVisible
            }
            btnActionPrev.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }

//    private fun showVideo(videoId: String) {
//        with(binding) {
//            actionCurrentVideo.findViewTreeLifecycleOwner()?.lifecycle?.addObserver(binding.actionCurrentVideo)
//
//            actionCurrentVideo.addYouTubePlayerListener(object :
//                AbstractYouTubePlayerListener() {
//                override fun onReady(youTubePlayer: YouTubePlayer) {
//                    youTubePlayer.cueVideo(videoId, 0f)
//                }
//            })
//        }
//        binding.actionCurrentVideo.visibility = View.VISIBLE
//    }

}