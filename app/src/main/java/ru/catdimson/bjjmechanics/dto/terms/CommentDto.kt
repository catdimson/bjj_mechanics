package ru.catdimson.bjjmechanics.dto.terms

class CommentDto(
    text: String?,
    userId: Int?,
    termId: Int?
) {
    private val text: String?
    private val userId: Int?
    private val termId: Int?

    init {
        this.text = text
        this.userId = userId
        this.termId = termId
    }
}