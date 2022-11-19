package ru.catdimson.bjjmechanics.ui.sections

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.catdimson.bjjmechanics.databinding.ItemCoachesBinding
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach

class CoachesViewHolder(
    private val binding: ItemCoachesBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): CoachesViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CoachesViewHolder(ItemCoachesBinding.inflate(inflater, parent, false))
        }
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Coach) {
        binding.apply {
            coachPreview.load(item.photoPreviewUrl)
            fio.text = "${item.person.lastName} ${item.person.firstName} ${item.person.middleName}"
            experience.text = "Тренерский стаж: ${item.experience} лет"
            belt.load(item.belt.beltPictureUrl)
            coachInfo.text = item.info
        }
    }

}