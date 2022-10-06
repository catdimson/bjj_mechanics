package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class DataSourceImpl(
    private val provider: RetrofitImpl
) : DataSource {

    override suspend fun findSectionsByTown(town: String): List<SectionInfo> {
        return provider.findSectionsByTown(town)
    }

    override suspend fun findSectionById(id: Int): SectionInfo {
        return provider.findSectionById(id)
    }

    override suspend fun findCoachesBySectionId(id: Int): List<Coach> {
        return provider.findCoachesBySectionId(id)
    }

    override suspend fun findCoachById(id: Int): Coach {
        return provider.findCoachById(id)
    }

}