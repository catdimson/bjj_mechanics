package ru.catdimson.bjjmechanics.domain.repository.sections

import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsRepositoryImpl(
    private val dataSource: DataSource
) : SectionsRepository {

    override suspend fun findByTitle(title: String, authMap: Map<String, String>): List<SectionInfo> {
        return dataSource.findSectionsByTitle(title, authMap)
    }

    override suspend fun findByCity(city: String, authMap: Map<String, String>): List<SectionInfo> {
        return dataSource.findSectionsByCity(city, authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): SectionInfo {
        return dataSource.findSectionById(id, authMap)
    }
}