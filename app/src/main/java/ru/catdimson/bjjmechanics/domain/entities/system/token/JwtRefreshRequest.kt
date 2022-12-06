package ru.catdimson.bjjmechanics.domain.entities.system.token

data class JwtRefreshRequest(
    val refreshToken: String
)