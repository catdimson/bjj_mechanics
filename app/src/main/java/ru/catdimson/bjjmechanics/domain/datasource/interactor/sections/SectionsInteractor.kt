package ru.catdimson.bjjmechanics.domain.datasource.interactor.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo

interface SectionsInteractor {

    suspend fun findByTitle(title: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findByCity(city: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findById(id: Int, authMap: Map<String, String>): SectionInfo

}