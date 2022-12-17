package ru.catdimson.bjjmechanics.ui.sections

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach

class CoachesAdapter : RecyclerView.Adapter<CoachesViewHolder>() {

    private var data: List<Coach> = arrayListOf()

    fun setData(data: List<Coach>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoachesViewHolder {
        return CoachesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CoachesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int): Coach = data[position]
}