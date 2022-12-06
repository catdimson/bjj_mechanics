package ru.catdimson.bjjmechanics.domain.entities.system.token

data class JwtRequest(
    val login: String,
    val password: String
)