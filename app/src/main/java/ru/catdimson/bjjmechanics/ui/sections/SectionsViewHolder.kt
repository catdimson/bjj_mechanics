package ru.catdimson.bjjmechanics.ui.sections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.catdimson.bjjmechanics.databinding.ItemSectionsBinding
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsViewHolder(
    private val binding: ItemSectionsBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): SectionsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return SectionsViewHolder(ItemSectionsBinding.inflate(inflater, parent, false))
        }
    }

    fun bind(item: SectionInfo, onItemClickListener: SectionsAdapter.OnListItemClickListener) {
        binding.apply {
            sectionPreview.load("https://i.pinimg.com/originals/e3/7b/ab/e37bab9df8343b528cc3a90abf507c7b.jpg"/*item.previewUrl*/)
            title.text = item.title
            address.text = item.address.location
            contacts.text =
                if (item.contacts.isNullOrEmpty()) "Пока пусто" else item.contacts[0].toString()
            shortDescription.text = item.shortDescription
        }
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
}