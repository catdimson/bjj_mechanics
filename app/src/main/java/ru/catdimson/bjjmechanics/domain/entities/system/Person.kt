package ru.catdimson.bjjmechanics.domain.entities.system

data class Person(
    val id: Int,

    val firstName: String,
    val middleName: String,
    val lastName: String,
    val birthday: String,

    val contactPhone: String?,

    val createDate: String,
    val editDate: String
)