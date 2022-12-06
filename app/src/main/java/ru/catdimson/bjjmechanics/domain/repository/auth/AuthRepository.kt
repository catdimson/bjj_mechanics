package ru.catdimson.bjjmechanics.domain.repository.auth

import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.repository.Repository

/**
 * Репозиторий для работы с регистрацией/аутенитификацией
 * */
interface AuthRepository : Repository {

    suspend fun login(jwtRequest: JwtRequest): JwtResponse

    suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun registration(regData: RegistrationData)

}