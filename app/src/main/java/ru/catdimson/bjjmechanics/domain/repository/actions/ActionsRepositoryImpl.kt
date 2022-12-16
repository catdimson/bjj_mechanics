package ru.catdimson.bjjmechanics.domain.repository.actions

import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.actions.Action

class ActionsRepositoryImpl(
    private val dataSource: DataSource
) : ActionsRepository {

    override suspend fun findStartingAction(): List<Action> {
        return dataSource.findStartingAction()
    }

    override suspend fun findById(id: Int): Action {
        return dataSource.findActionById(id)
    }

    override suspend fun findByPrevId(prevId: Int): List<Action> {
        return dataSource.findActionsByPrevId(prevId)
    }
}