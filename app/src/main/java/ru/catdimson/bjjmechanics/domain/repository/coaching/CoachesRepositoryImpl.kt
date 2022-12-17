package ru.catdimson.bjjmechanics.domain.repository.coaching

import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.sections.Coach

class CoachesRepositoryImpl(
    private val dataSource: DataSource
) : CoachesRepository {

    override suspend fun findBySection(id: Int): List<Coach> {
        return dataSource.findCoachesBySection(id)
    }

    override suspend fun findById(id: Int): Coach {
        return dataSource.findCoachById(id)
    }
}