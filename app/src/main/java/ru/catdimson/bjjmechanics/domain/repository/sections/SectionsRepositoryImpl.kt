package ru.catdimson.bjjmechanics.domain.repository.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

class SectionsRepositoryImpl : SectionsRepository {
    override suspend fun findByTown(town: String): List<SectionInfo> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Int): SectionInfo {
        TODO("Not yet implemented")
    }
}