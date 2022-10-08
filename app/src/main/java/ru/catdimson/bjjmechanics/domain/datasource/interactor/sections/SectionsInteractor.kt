package ru.catdimson.bjjmechanics.domain.datasource.interactor.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface SectionsInteractor {

    suspend fun findByTitle(title: String): List<SectionInfo>

    suspend fun findByCity(city: String): List<SectionInfo>

    suspend fun findById(id: Int): SectionInfo

}