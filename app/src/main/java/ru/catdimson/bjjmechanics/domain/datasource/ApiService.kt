package ru.catdimson.bjjmechanics.domain.datasource

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface ApiService {

    // Sections
    @GET("sections")
    fun findSectionsByTitle(title: String): Deferred<List<SectionInfo>>

    @GET("sections")
    fun findSectionsByCity(city: String): Deferred<List<SectionInfo>>

    @GET("sections/{id}")
    fun findSectionById(@Path("id") id: Int): Deferred<SectionInfo>

    // Coaches
    @GET("sections/{id}/coaches")
    fun findCoachesBySectionId(@Path("id") id: Int): Deferred<List<Coach>>

    @GET("coach/{id}")
    fun findCoachById(@Path("id") id: Int): Deferred<Coach>

}