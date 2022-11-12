package ru.catdimson.bjjmechanics.domain.datasource

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

interface DataSource {

    // sections
    suspend fun findSectionsByTitle(title: String): List<SectionInfo>

    suspend fun findSectionsByCity(city: String): List<SectionInfo>

    suspend fun findSectionById(id: Int): SectionInfo

    // coaches
    suspend fun findCoachesBySection(id: Int): List<Coach>

    suspend fun findCoachById(id: Int): Coach

    // terms
    suspend fun findAllTerms(authMap: Map<String, String>): List<Term>

    suspend fun findTermById(id: Int): Term

}