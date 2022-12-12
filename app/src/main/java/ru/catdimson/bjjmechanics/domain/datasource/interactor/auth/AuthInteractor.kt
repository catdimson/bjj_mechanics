package ru.catdimson.bjjmechanics.domain.datasource.interactor.auth

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

interface AuthInteractor {

    suspend fun login(jwtRequest: JwtRequest): JwtResponse

    suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun registration(regData: RegistrationData): Response<Void>

}