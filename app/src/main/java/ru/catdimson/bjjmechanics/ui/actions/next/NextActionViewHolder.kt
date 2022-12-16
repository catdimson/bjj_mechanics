package ru.catdimson.bjjmechanics.ui.actions.next

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
import ru.catdimson.bjjmechanics.databinding.ItemActionNextBinding
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.ui.actions.current.CurrentActionAdapter
import ru.catdimson.bjjmechanics.ui.actions.current.CurrentActionViewHolder
import ru.catdimson.bjjmechanics.utils.extractYoutubeId

class NextActionViewHolder(
    private val binding: ItemActionNextBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): NextActionViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return NextActionViewHolder(
                ItemActionNextBinding.inflate(inflater, parent, false)
            )
        }
    }

    fun bind(item: Action, onItemClickListener: NextActionAdapter.OnListItemClickListener) {
        binding.apply {
//            showVideo(extractYoutubeId(item.video.url))
            nextActionImage.load("https://i.ytimg.com/vi/OCt9qux--vo/maxresdefault.jpg")
            actionTitle.text = item.title
            btnReadDetails.setOnClickListener {
                actionDescription.isVisible = !actionDescription.isVisible
            }
            btnActionNext.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }

//    private fun showVideo(videoId: String) {
//        with(binding) {
//            actionNextVideo.findViewTreeLifecycleOwner()?.lifecycle?.addObserver(binding.actionNextVideo)
//
//            actionNextVideo.addYouTubePlayerListener(object :
//                AbstractYouTubePlayerListener() {
//                override fun onReady(youTubePlayer: YouTubePlayer) {
//                    youTubePlayer.cueVideo(videoId, 0f)
//                }
//            })
//        }
//        binding.actionNextVideo.visibility = View.VISIBLE
//    }

}