package ru.catdimson.bjjmechanics.domain.datasource.interactor.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.repository.sections.SectionsRepository

class SectionsInteractorImpl(
    private val repository: SectionsRepository
) : SectionsInteractor {

    override suspend fun findByTitle(
        title: String,
        authMap: Map<String, String>
    ): List<SectionInfo> {
        return repository.findByTitle(title, authMap)
    }

    override suspend fun findByCity(city: String, authMap: Map<String, String>): List<SectionInfo> {
        return repository.findByCity(city, authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): SectionInfo {
        return repository.findById(id, authMap)
    }

}