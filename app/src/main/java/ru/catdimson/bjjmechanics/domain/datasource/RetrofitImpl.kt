package ru.catdimson.bjjmechanics.domain.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.system.RegistrationData
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRefreshRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtRequest
import ru.catdimson.bjjmechanics.domain.entities.system.token.JwtResponse
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

class RetrofitImpl : DataSource {

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(DEBUG_API_URL)
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
        private const val DEBUG_API_URL = "http://10.0.2.2:8080/"
    }

    // actions
    override suspend fun findActionById(id: Int): Action {
        return getService().findActionById(id).await()
    }

    override suspend fun findActionsByPrevId(prevId: Int): List<Action> {
        return getService().findActionsByPrevId(prevId).await()
    }

    // sections
    override suspend fun findSectionsByTitle(
        title: String,
        authMap: Map<String, String>
    ): List<SectionInfo> {
        return getService().findSectionsByTitle(title, authMap).await()
    }

    override suspend fun findSectionsByCity(
        city: String,
        authMap: Map<String, String>
    ): List<SectionInfo> {
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

    override suspend fun saveTermComment(
        commentDto: CommentDto,
        tokens: Map<String, String>
    ): Response<Void> {
        return getService().saveTermComment(commentDto, tokens).await()
    }

    // auth
    override suspend fun login(jwtRequest: JwtRequest): JwtResponse {
        return getService().login(jwtRequest).await()
    }

    override suspend fun token(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return getService().token(jwtRefreshRequest).await()
    }

    override fun refresh(jwtRefreshRequest: JwtRefreshRequest): JwtResponse {
        return getService().refresh(jwtRefreshRequest)
    }

    override suspend fun registration(regData: RegistrationData): Response<Void> {
        return getService().registration(regData).await()
    }
}