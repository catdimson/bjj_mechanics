package ru.catdimson.bjjmechanics.domain.entities.system.token

import ru.catdimson.bjjmechanics.domain.entities.system.ShortUserInfo

data class JwtResponse(
    val type: String,
    val accessToken: String,
    val refreshToken: String,
    val user: ShortUserInfo
)