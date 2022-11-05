package ru.catdimson.bjjmechanics.domain.entities.sections

import ru.catdimson.bjjmechanics.domain.entities.general.Address

data class SectionInfo(
    val id: Int,

    val title: String,
    val shortDescription: String,
    val fullDescription: String,

    val previewUrl: String,
    val imageUrl: String,

    val address: Address,
    val coaches: List<Coach>,
    val isActive: Boolean
)
