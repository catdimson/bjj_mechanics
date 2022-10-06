package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface DataSource {

    suspend fun findSectionsByTown(town: String): List<SectionInfo>

    suspend fun findSectionById(id: Int): SectionInfo

    suspend fun findCoachesBySectionId(id: Int): List<Coach>

    suspend fun findCoachById(id: Int): Coach

}