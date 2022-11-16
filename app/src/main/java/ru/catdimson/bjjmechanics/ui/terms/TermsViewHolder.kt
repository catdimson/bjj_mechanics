package ru.catdimson.bjjmechanics.ui.terms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.catdimson.bjjmechanics.databinding.ItemTermsBinding
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

class TermsViewHolder(
    private val binding: ItemTermsBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): TermsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return TermsViewHolder(ItemTermsBinding.inflate(inflater, parent, false))
        }
    }

    fun bind(item: Term, onItemClickListener: TermsAdapter.OnListItemClickListener) {
        binding.apply {
            name.text = item.name
            termType.text = item.termType.title
            readMore.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }

}