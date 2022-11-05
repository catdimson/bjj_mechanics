package ru.catdimson.bjjmechanics.domain.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

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
        private const val BASE_API_URL = "http://45.144.2.195:8080/api/v1/"
    }

    override suspend fun findSectionsByTitle(title: String): List<SectionInfo> {
        return getService().findSectionsByTitle(title).await()
    }

    override suspend fun findSectionsByCity(city: String): List<SectionInfo> {
        return getService().findSectionsByCity(city).await()
    }

    override suspend fun findSectionById(id: Int): SectionInfo {
        return getService().findSectionById(id).await()
    }

    override suspend fun findCoachesBySection(id: Int): List<Coach> {
        return getService().findCoachesBySectionId(id).await()
    }

    override suspend fun findCoachById(id: Int): Coach {
        return getService().findCoachById(id).await()
    }
}