package ru.catdimson.bjjmechanics.domain.entities.system

import ru.catdimson.bjjmechanics.domain.entities.user.Person

data class RegistrationData(
    val login: String?,
    val password: String?
)