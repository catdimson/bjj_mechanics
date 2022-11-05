package ru.catdimson.bjjmechanics.domain.entities.terms

import ru.catdimson.bjjmechanics.domain.entities.system.User

data class Comment(
    val id: Int,
    val text: String,
    val user: User
)