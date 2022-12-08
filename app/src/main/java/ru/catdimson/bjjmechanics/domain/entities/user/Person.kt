package ru.catdimson.bjjmechanics.domain.entities.user

import java.time.LocalDate

data class Person(
    val id: Int,

    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthday: LocalDate?,

    val phone: String
)