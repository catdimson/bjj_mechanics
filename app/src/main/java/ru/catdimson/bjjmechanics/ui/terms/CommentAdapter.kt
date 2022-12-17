package ru.catdimson.bjjmechanics.ui.terms

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.terms.Comment

class CommentAdapter() : RecyclerView.Adapter<CommentViewHolder>() {

    private var data: MutableList<Comment> = mutableListOf()

    fun setData(data: List<Comment>?) {
        if (data != null) {
            this.data = data as MutableList
        }
        notifyDataSetChanged()
    }

    fun addData(comment: Comment) {
        data.add(comment)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Comment = data[position]

    interface OnListItemClickListener {
        fun onItemClick(data: Comment)
    }
}