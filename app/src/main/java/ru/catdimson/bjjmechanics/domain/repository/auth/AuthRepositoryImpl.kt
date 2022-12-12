package ru.catdimson.bjjmechanics.domain.repository.auth

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse

class AuthRepositoryImpl(
    private val dataSource: DataSource
) : AuthRepository {

    override suspend fun login(jwtRequest: JwtRequest): JwtResponse {
       return dataSource.login(jwtRequest)
    }

    override suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return dataSource.token(jwtRefreshRequest)
    }

    override fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return dataSource.refresh(jwtRefreshRequest)
    }

    override suspend fun registration(regData: RegistrationData): Response<Void> {
        return dataSource.registration(regData)
    }
}