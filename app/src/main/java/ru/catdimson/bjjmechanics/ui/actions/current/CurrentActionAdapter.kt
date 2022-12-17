package ru.catdimson.bjjmechanics.ui.actions.current

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.ui.actions.OnListItemButtonClickListener

class CurrentActionAdapter(
    private val onListItemClickListenerToPrev: OnListItemClickListener,
    private val onListItemButtonClickListener: OnListItemButtonClickListener
) : RecyclerView.Adapter<CurrentActionViewHolder>() {

    private var data: List<Action> = arrayListOf()

    fun setData(data: List<Action>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentActionViewHolder {
        return CurrentActionViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CurrentActionViewHolder, position: Int) {
        holder.bind(getItem(position), onListItemClickListenerToPrev, onListItemButtonClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Action = data[position]

    interface OnListItemClickListener {
        fun onItemClick(data: Action)
    }
}