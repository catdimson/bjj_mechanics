package ru.catdimson.bjjmechanics.domain.repository.terms

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.repository.Repository
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

/**
 * Репозиторий для работы с Терминами
 * */
interface TermsRepository : Repository {

    suspend fun findAll(authMap: Map<String, String>): List<Term>

    suspend fun findById(id: Int, authMap: Map<String, String>): Term

    suspend fun saveTermComment(commentDto: CommentDto, tokens: Map<String, String>): Response<Void>

}