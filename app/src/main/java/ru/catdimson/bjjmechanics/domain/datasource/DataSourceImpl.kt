package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class DataSourceImpl(
    private val provider: RetrofitImpl
) : DataSource {

    override suspend fun findSectionsByTitle(title: String): List<SectionInfo> {
        return provider.findSectionsByTitle(title)
    }

    override suspend fun findSectionsByCity(city: String): List<SectionInfo> {
        return provider.findSectionsByCity(city)
    }

    override suspend fun findSectionById(id: Int): SectionInfo {
        return provider.findSectionById(id)
    }

    override suspend fun findCoachesBySection(id: Int): List<Coach> {
        return provider.findCoachesBySection(id)
    }

    override suspend fun findCoachById(id: Int): Coach {
        return provider.findCoachById(id)
    }

}