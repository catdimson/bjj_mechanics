package ru.catdimson.bjjmechanics.domain.entities.actions

import ru.catdimson.bjjmechanics.domain.entities.terms.Video

data class Action(
    val id: Int,
    val title: String,
    val description: String,
    val actionType: ActionType,
    val video: Video,
    val imageUrl: String?,

    var isStart: Boolean = false,
    val isFinish: Boolean = false,

    val prevActions: List<Action>,
    val nextActions: List<Action>
)