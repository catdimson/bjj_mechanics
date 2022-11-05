package ru.catdimson.bjjmechanics.domain.entities.education

import ru.catdimson.bjjmechanics.domain.entities.terms.Video

data class Action(
    val id: Int,
    val title: String,
    val description: String,
    val actionType: ActionType,
    val video: Video,

    val prevActions: List<Action>,
    val nextActions: List<Action>
)