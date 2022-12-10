package ru.catdimson.bjjmechanics.domain.entities.user

data class User(
    val id: Int,
    val login: String,
    val password: String?,
    val role: Role?,
    val isActive: Int?,
    val person: Person?
)