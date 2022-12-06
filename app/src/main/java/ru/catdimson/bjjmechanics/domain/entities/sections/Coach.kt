package ru.catdimson.bjjmechanics.domain.entities.sections

import ru.catdimson.bjjmechanics.domain.entities.user.Person

data class Coach(
    val id: Int,

    val info: String,
    val experience: Int,

    val person: Person,
    val belt: Belt,

    val photoUrl: String,
    val photoPreviewUrl: String
)