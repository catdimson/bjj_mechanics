package ru.catdimson.bjjmechanics.ui.terms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.databinding.ItemCommentBinding
import ru.catdimson.bjjmechanics.domain.entities.terms.Comment

class CommentViewHolder(
    private val binding: ItemCommentBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CommentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CommentViewHolder(ItemCommentBinding.inflate(inflater))
        }
    }

    fun bind(item: Comment, onItemClickListener: CommentAdapter.OnListItemClickListener) {
        binding.apply {
            login.text = item.user.login
            createDate.text = item.createDate.toString()
            text.text = item.text
        }
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }

}