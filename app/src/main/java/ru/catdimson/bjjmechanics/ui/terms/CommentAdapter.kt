package ru.catdimson.bjjmechanics.ui.terms

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.terms.Comment

class CommentAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<CommentViewHolder>() {

    private var data: List<Comment> = arrayListOf()

    fun setData(data: List<Comment>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position), onListItemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Comment = data[position]

    interface OnListItemClickListener {
        fun onItemClick(data: Comment)
    }
}