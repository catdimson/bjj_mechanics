package ru.catdimson.bjjmechanics.domain.repository.actions

import ru.catdimson.bjjmechanics.domain.entities.actions.Action

interface ActionsRepository {

    suspend fun findById(id: Int): Action

    suspend fun findByPrevId(prevId: Int): List<Action>

}