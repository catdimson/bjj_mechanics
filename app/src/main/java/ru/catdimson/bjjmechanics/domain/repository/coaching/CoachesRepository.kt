package ru.catdimson.bjjmechanics.domain.repository.coaching

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.repository.Repository

/**
 * Репозиторий для работы с Тренерами
 * */
interface CoachesRepository : Repository {

    suspend fun findBySection(id: Int): List<Coach>

    suspend fun findById(id: Int): Coach

}