package ru.catdimson.bjjmechanics.domain.datasource.interactor.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface SectionsInteractor {

    suspend fun findByTown(town: String): List<SectionInfo>

    suspend fun findById(id: Int): SectionInfo

}