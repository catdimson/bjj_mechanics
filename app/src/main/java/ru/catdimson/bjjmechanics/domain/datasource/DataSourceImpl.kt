package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

class DataSourceImpl(
    private val provider: RetrofitImpl
) : DataSource {

    // sections
    override suspend fun findSectionsByTitle(title: String, authMap: Map<String, String>): List<SectionInfo> {
        return provider.findSectionsByTitle(title, authMap)
    }

    override suspend fun findSectionsByCity(city: String, authMap: Map<String, String>): List<SectionInfo> {
        return provider.findSectionsByCity(city, authMap)
    }

    override suspend fun findSectionById(id: Int, authMap: Map<String, String>): SectionInfo {
        return provider.findSectionById(id, authMap)
    }

    // coaches
    override suspend fun findCoachesBySection(id: Int): List<Coach> {
        return provider.findCoachesBySection(id)
    }

    override suspend fun findCoachById(id: Int): Coach {
        return provider.findCoachById(id)
    }

    // terms
    override suspend fun findAllTerms(authMap: Map<String, String>): List<Term> {
        return provider.findAllTerms(authMap)
    }

    override suspend fun findTermById(id: Int, authMap: Map<String, String>): Term {
        return provider.findTermById(id, authMap)
    }

}