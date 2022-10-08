package ru.catdimson.bjjmechanics.domain.datasource.interactor.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepository

class SectionsInteractorImpl(
    private val repository: SectionsRepository
) : SectionsInteractor{
    override suspend fun findByTown(town: String): List<SectionInfo> {
        return repository.findByTown(town)
    }

    override suspend fun findById(id: Int): SectionInfo {
        return repository.findById(id)
    }

}