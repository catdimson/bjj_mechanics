package ru.catdimson.bjjmechanics.domain.repository.sections

import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsRepositoryImpl(
    private val dataSource: DataSource
) : SectionsRepository {

    override suspend fun findByTitle(title: String): List<SectionInfo> {
        return dataSource.findSectionsByTitle(title)
    }

    override suspend fun findByCity(city: String): List<SectionInfo> {
        return dataSource.findSectionsByCity(city)
    }

    override suspend fun findById(id: Int): SectionInfo {
        return dataSource.findSectionById(id)
    }
}