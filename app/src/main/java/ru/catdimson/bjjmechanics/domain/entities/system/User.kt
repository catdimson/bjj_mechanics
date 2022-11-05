package ru.catdimson.bjjmechanics.domain.entities.system

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val role: Role,
    val isActive: Boolean,
    val person: Person
)