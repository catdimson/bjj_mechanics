package ru.catdimson.bjjmechanics.ui.sections

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsAdapter : RecyclerView.Adapter<SectionsViewHolder>() {

    private var data : List<SectionInfo> = arrayListOf()

    fun setData(data: List<SectionInfo>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsViewHolder {
        return SectionsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(position: Int) : SectionInfo = data[position]

}