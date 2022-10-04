package ru.catdimson.bjjmechanics.domain.entities.general

data class Contact(
    val id: Int,
    val type: ContactType,
    val value: String,

    val createDate: String,
    val editDate: String
)