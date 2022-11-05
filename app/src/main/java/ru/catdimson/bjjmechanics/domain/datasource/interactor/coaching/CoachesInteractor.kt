package ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach

interface CoachesInteractor {

    suspend fun findBySection(id: Int): List<Coach>

    suspend fun findById(id: Int): Coach

}