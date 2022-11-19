package ru.catdimson.bjjmechanics.domain.datasource

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path
import retrofit2.http.Query
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

interface ApiService {

    // Sections
    @GET("section_info")
    fun findSectionsByTitle(
        @Query("title") title: String,
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<SectionInfo>>

    @GET("section_info")
    fun findSectionsByCity(
        @Query("city") city: String,
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<SectionInfo>>

    @GET("section_info/{id}")
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
    @GET("term")
    fun findAllTerms(
        @HeaderMap authMap: Map<String, String>
    ): Deferred<List<Term>>

    @GET("term/{id}")
    fun findTermById(
        @HeaderMap authMap: Map<String, String>,
        @Path("id") id: Int
    ): Deferred<Term>

}