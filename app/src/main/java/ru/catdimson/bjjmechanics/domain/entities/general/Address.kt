package ru.catdimson.bjjmechanics.domain.entities.general

import ru.catdimson.bjjmechanics.domain.entities.coaching.City

data class Address(
    val id: Int,
    val address: String,

    val createDate: String,
    val editDate: String,

    val city: City
)