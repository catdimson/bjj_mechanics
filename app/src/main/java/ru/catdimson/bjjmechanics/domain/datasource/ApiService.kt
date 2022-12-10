package ru.catdimson.bjjmechanics.domain.datasource

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

interface ApiService {

    // Sections
    @GET("sections")
    fun findSectionsByTitle(
        @Query("title") title: String,
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<SectionInfo>>

    @GET("sections")
    fun findSectionsByCity(
        @Query("city") city: String,
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<SectionInfo>>

    @GET("sections/{id}")
    fun findSectionById(
        @Path("id") id: Int,
        @HeaderMap authMap: Map<String, String>
    ): Deferred<SectionInfo>

    // Coaches
    @GET("sections/{id}/coaches")
    fun findCoachesBySectionId(
        @Path("id") id: Int
    ): Deferred<List<Coach>>

    @GET("coach/{id}")
    fun findCoachById(
        @Path("id") id: Int
    ): Deferred<Coach>

    // Terms
    @GET("terms")
    fun findAllTerms(
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<Term>>

    @GET("terms/{id}")
    fun findTermById(
        @HeaderMap authMap: Map<String, String>,
        @Path("id") id: Int
    ): Deferred<Term>

    @GET("terms/comments")
    fun saveTermComment(
        @Body commentDto: CommentDto,
        @HeaderMap tokens: Map<String, String>
    ): Deferred<Response<Void>>

    // Auth
    @POST("auth/")
    fun registration(
        @Body regData: RegistrationData
    ): Deferred<Response<Void>>

    @POST("auth/login")
    fun login(
        @Body jwtRequest: JwtRequest
    ): Deferred<JwtResponse>

    @POST("auth/token")
    fun token(
        @Body jwtRefreshRequest: JwtRefreshRequest
    ): Deferred<JwtResponse>

    @POST("auth/refresh")
    fun refresh(
        @Body jwtRefreshRequest: JwtRefreshRequest
    ): Deferred<JwtResponse>
}