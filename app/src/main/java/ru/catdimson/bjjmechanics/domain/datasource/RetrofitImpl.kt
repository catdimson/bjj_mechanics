package ru.catdimson.bjjmechanics.domain.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

class RetrofitImpl : DataSource {

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        retrofit.create(ApiService::class.java)
    }

    private fun getService(): ApiService {
        return api
    }

    companion object {
        private const val BASE_API_URL = "http://45.144.2.195:8080/"
    }

    // sections
    override suspend fun findSectionsByTitle(title: String, authMap: Map<String, String>): List<SectionInfo> {
        return getService().findSectionsByTitle(title, authMap).await()
    }

    override suspend fun findSectionsByCity(city: String, authMap: Map<String, String>): List<SectionInfo> {
        return getService().findSectionsByCity(city, authMap).await()
    }

    override suspend fun findSectionById(id: Int, authMap: Map<String, String>): SectionInfo {
        return getService().findSectionById(id, authMap).await()
    }

    override suspend fun findCoachesBySection(id: Int): List<Coach> {
        return getService().findCoachesBySectionId(id).await()
    }

    override suspend fun findCoachById(id: Int): Coach {
        return getService().findCoachById(id).await()
    }

    // terms
    override suspend fun findAllTerms(authMap: Map<String, String>): List<Term> {
        return getService().findAllTerms(authMap).await()
    }

    override suspend fun findTermById(id: Int, authMap: Map<String, String>): Term {
        return getService().findTermById(authMap, id).await()
    }
}