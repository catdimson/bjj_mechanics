package ru.catdimson.bjjmechanics.domain.entities.general

data class Address(
    val id: Int,
    val location: String,
    val city: City
)