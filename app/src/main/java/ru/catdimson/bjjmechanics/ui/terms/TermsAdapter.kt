package ru.catdimson.bjjmechanics.ui.terms

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

class TermsAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<TermsViewHolder>() {

    private var data: List<Term> = arrayListOf()

    fun setData(data: List<Term>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        return TermsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        holder.bind(getItem(position), onListItemClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Term = data[position]

    interface OnListItemClickListener {
        fun onItemClick(data: Term)
    }
}