package ru.catdimson.bjjmechanics.ui.actions.next

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.catdimson.bjjmechanics.databinding.ItemActionNextBinding
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.ui.actions.OnListItemButtonClickListener

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

    fun bind(
        item: Action,
        onItemClickListener: NextActionAdapter.OnListItemClickListener,
        onListItemButtonClickListener: OnListItemButtonClickListener
    ) {
        binding.apply {
//            showVideo(extractYoutubeId(item.video.url))
            nextActionImage.load("https://i.ytimg.com/vi/OCt9qux--vo/maxresdefault.jpg")
            actionTitle.text = item.title
            btnVideoDetails.setOnClickListener {
                onListItemButtonClickListener.onVideoButtonClick(item.video.terms?.get(0)?.id)
            }
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