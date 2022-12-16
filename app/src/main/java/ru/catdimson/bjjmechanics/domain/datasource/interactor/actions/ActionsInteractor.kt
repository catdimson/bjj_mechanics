package ru.catdimson.bjjmechanics.domain.datasource.interactor.actions

import ru.catdimson.bjjmechanics.domain.entities.actions.Action

interface ActionsInteractor {

    suspend fun findStartingAction(): List<Action>

    suspend fun findById(id: Int): Action

    suspend fun findByPrevId(prevId: Int): List<Action>

}