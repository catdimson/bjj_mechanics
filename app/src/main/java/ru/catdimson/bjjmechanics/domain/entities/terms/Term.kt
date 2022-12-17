package ru.catdimson.bjjmechanics.domain.entities.terms

data class Term(
    val id: Int,
    val name: String?,
    val description: String?,
    val video: Video?,
    val termType: TermType?,
    val comments: List<Comment>?
)