package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface DataSource {

    // sections
    suspend fun findSectionsByTitle(title: String): List<SectionInfo>

    suspend fun findSectionsByCity(city: String): List<SectionInfo>

    suspend fun findSectionById(id: Int): SectionInfo

    // coaches
    suspend fun findCoachesBySection(id: Int): List<Coach>

    suspend fun findCoachById(id: Int): Coach

}