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

    fun bind(item: SectionInfo) {
        binding.apply {
            sectionPreview.load(item.previewUrl)
            sectionTitle.text = item.title
            sectionDescription.text = item.shortDescription
        }
    }

}