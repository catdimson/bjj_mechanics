package ru.catdimson.bjjmechanics.domain.entities.terms

import ru.catdimson.bjjmechanics.domain.entities.user.User

data class Comment(
    val id: Int?,
    val text: String,
    val userId: Int,
    val termId: Int
//    val createDate: LocalDate
)