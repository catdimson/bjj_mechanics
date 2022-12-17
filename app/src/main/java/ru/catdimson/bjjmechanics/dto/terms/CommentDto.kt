package ru.catdimson.bjjmechanics.dto.terms

import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.entities.user.User

class CommentDto(
    text: String?,
    user: User?,
    term: Term?
) {
    private val text: String?
    private val user: User?
    private val term: Term?

    init {
        this.text = text
        this.user = user
        this.term = term
    }
}