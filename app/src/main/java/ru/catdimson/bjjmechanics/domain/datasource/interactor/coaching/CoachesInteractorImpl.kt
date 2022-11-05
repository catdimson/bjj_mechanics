package ru.catdimson.bjjmechanics.domain.datasource.interactor.coaching

import ru.catdimson.bjjmechanics.domain.entities.sections.Coach
import ru.catdimson.bjjmechanics.domain.repository.coaching.CoachesRepository

class CoachesInteractorImpl(
    private val repository: CoachesRepository
) : CoachesInteractor {
    override suspend fun findBySection(id: Int): List<Coach> {
        return repository.findBySection(id)
    }

    override suspend fun findById(id: Int): Coach {
        return repository.findById(id)
    }

}