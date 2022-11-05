package ru.catdimson.bjjmechanics.ui.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.catdimson.bjjmechanics.databinding.ItemSectionsBinding
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsViewHolder(
    private val binding: ItemSectionsBinding
): RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) : SectionsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return SectionsViewHolder(ItemSectionsBinding.inflate(inflater))
        }
    }

    fun bind(item: SectionInfo, onItemClickListener: SectionsAdapter.OnListItemClickListener) {
        binding.apply {
            sectionPreview.load(item.previewUrl)
            title.text = item.title
            address.text = item.address.location
            contacts.text = item.contacts.toString()
            shortDescription.text = item.shortDescription
        }
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
}