package ru.catdimson.bjjmechanics.domain.datasource

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

interface DataSource {

    // actions
    suspend fun findStartingAction(): List<Action>

    suspend fun findActionById(id: Int): Action

    suspend fun findActionsByPrevId(prevId: Int): List<Action>

    // sections
    suspend fun findSectionsByTitle(title: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findSectionsByCity(city: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findSectionById(id: Int, authMap: Map<String, String>): SectionInfo

    // coaches
    suspend fun findCoachesBySection(id: Int): List<Coach>

    suspend fun findCoachById(id: Int): Coach

    // terms
    suspend fun findAllTerms(authMap: Map<String, String>): List<Term>

    suspend fun findTermById(id: Int): Term

    suspend fun saveTermComment(commentDto: CommentDto, authorization: Map<String, String>): Response<Void>

    // auth
    suspend fun login(jwtRequest: JwtRequest): JwtResponse

    suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse

    suspend fun refresh(jwtRefreshRequest: JwtRefreshRequest, authorization: Map<String, String>): JwtResponse

    suspend fun registration(regData: RegistrationData): Response<Void>

}