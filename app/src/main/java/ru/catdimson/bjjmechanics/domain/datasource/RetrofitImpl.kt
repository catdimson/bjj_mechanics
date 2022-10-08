package ru.catdimson.bjjmechanics.domain.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach
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
        private const val BASE_API_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }

    override suspend fun findSectionsByTown(town: String): List<SectionInfo> {
        return getService().findSectionsByTown(town).await()
    }

    override suspend fun findSectionById(id: Int): SectionInfo {
        return getService().findSectionById(id).await()
    }

    override suspend fun findCoachesBySectionId(id: Int): List<Coach> {
        return getService().findCoachesBySectionId(id).await()
    }

    override suspend fun findCoachById(id: Int): Coach {
        return getService().findCoachById(id).await()
    }
}