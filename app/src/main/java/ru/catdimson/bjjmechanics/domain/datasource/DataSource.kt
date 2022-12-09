package ru.catdimson.bjjmechanics.domain.datasource

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

interface DataSource {

    // sections
    suspend fun findSectionsByTitle(title: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findSectionsByCity(city: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findSectionById(id: Int, authMap: Map<String, String>): SectionInfo

    // coaches
    suspend fun findCoachesBySection(id: Int): List<Coach>

    suspend fun findCoachById(id: Int): Coach

    // terms
    suspend fun findAllTerms(authMap: Map<String, String>): List<Term>

    suspend fun findTermById(id: Int, authMap: Map<String, String>): Term

    // auth
    suspend fun login(jwtRequest: JwtRequest): JwtResponse

    suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun registration(regData: RegistrationData): Response<Void>

}