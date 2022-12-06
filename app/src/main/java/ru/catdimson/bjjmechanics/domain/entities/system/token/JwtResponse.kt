package ru.catdimson.bjjmechanics.domain.entities.system.token

data class JwtResponse(
    val type: String,
    val accessToken: String,
    val refreshToken: String
)