package ru.catdimson.bjjmechanics.domain.entities.sections

data class Belt(
    val id: Int,
    val color: String,
    val stripes: Int?,
    val beltPictureUrl: String
)