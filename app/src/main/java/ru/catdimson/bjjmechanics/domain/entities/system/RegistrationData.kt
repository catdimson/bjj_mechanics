package ru.catdimson.bjjmechanics.domain.entities.system

data class RegistrationData(
    val login: String,
    val password: String,
    val name: String,
    val phone: String
)