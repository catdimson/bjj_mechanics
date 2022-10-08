package ru.catdimson.bjjmechanics.domain.repository.coaching

import ru.catdimson.bjjmechanics.domain.entities.coaching.Coach

class CoachesRepositoryImpl : CoachesRepository {
    override suspend fun findBySection(id: Int): List<Coach> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Int): Coach {
        TODO("Not yet implemented")
    }
}