package ru.catdimson.bjjmechanics.domain.entities.section

import ru.catdimson.bjjmechanics.domain.entities.general.Address
import ru.catdimson.bjjmechanics.domain.entities.general.Contact
import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach

data class SectionInfo(
    val id: Int,

    val title: String,
    val shortDescription: String,
    val fullDescription: String,

    val previewUrl: String,
    val imageUrl: String,

    val address: Address,
    val contacts: List<Contact>,
    val coachCollection: List<Coach>,
    val isActive: Boolean,

    val createDate: String,
    val editDate: String
)
