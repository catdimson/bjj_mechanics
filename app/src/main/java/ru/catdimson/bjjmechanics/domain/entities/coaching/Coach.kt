package ru.catdimson.bjjmechanics.domain.entities.coaching

import ru.catdimson.bjjmechanics.domain.entities.general.Person

data class Coach(
    val id: Int,

    val info: String,
    val experience: Int,

    val person: Person,
    val belt: Belt,

    val createDate: String,
    val editDate: String
)