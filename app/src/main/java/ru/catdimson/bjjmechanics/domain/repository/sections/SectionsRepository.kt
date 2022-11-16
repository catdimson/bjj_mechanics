package ru.catdimson.bjjmechanics.domain.repository.sections

import ru.catdimson.bjjmechanics.domain.entities.sections.SectionInfo
import ru.catdimson.bjjmechanics.domain.repository.Repository

/**
 * Репозиторий для работы с Секциями
 * */
interface SectionsRepository : Repository {

    suspend fun findByTitle(title: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findByCity(city: String, authMap: Map<String, String>): List<SectionInfo>

    suspend fun findById(id: Int, authMap: Map<String, String>): SectionInfo

}