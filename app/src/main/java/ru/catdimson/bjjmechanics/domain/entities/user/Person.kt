package ru.catdimson.bjjmechanics.domain.entities.user

import java.time.LocalDate

data class Person(
    val id: Int?,

    var firstName: String,
    val middleName: String?,
    val lastName: String?,

    val phone: String,
    val birthday: LocalDate?,
)