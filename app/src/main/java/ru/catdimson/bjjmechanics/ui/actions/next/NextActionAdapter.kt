package ru.catdimson.bjjmechanics.ui.actions.next

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.actions.Action

class NextActionAdapter(
    private val onListItemClickListenerToNext: OnListItemClickListener
) : RecyclerView.Adapter<NextActionViewHolder>() {
    private var data: List<Action> = arrayListOf()

    fun setData(data: List<Action>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextActionViewHolder {
        return NextActionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NextActionViewHolder, position: Int) {
        holder.bind(getItem(position), onListItemClickListenerToNext)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Action = data[position]

    interface OnListItemClickListener {
        fun onItemClick(data: Action)
    }
}