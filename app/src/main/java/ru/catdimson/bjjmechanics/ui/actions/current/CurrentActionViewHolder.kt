package ru.catdimson.bjjmechanics.ui.actions.current

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import ru.catdimson.bjjmechanics.databinding.ItemActionCurrentBinding
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.utils.extractYoutubeId

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

    fun bind(item: Action, onItemClickListener: CurrentActionAdapter.OnListItemClickListener) {
        binding.apply {
//            showVideo(extractYoutubeId(item.video.url))
            currentActionImage.load("https://armlock.com/wp-content/uploads/2015/12/lodi-brazilian-jiu-jitsu.jpg")
            actionTitle.text = item.title
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