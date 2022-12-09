package ru.catdimson.bjjmechanics.domain.datasource.interactor.auth

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.repository.auth.AuthRepository

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {

    override suspend fun login(jwtRequest: JwtRequest): JwtResponse {
        return repository.login(jwtRequest)
    }

    override suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return repository.token(jwtRefreshRequest)
    }

    override suspend fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return repository.refresh(jwtRefreshRequest)
    }

    override suspend fun registration(regData: RegistrationData): Response<Void> {
        return repository.registration(regData)
    }

}