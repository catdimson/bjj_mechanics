package ru.catdimson.bjjmechanics.domain.datasource.interactor.actions

import ru.catdimson.bjjmechanics.domain.entities.actions.Action
import ru.catdimson.bjjmechanics.domain.repository.actions.ActionsRepository

class ActionsInteractorImpl(
    private val repository: ActionsRepository
) : ActionsInteractor {

    override suspend fun findStartingAction(): List<Action> {
        return repository.findStartingAction()
    }

    override suspend fun findById(id: Int): Action {
        return repository.findById(id)
    }

    override suspend fun findByPrevId(prevId: Int): List<Action> {
        return repository.findByPrevId(prevId)
    }

}