package ru.catdimson.bjjmechanics.domain.datasource.interactor.terms

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

interface TermsInteractor {

    suspend fun findAll(authMap: Map<String, String>): List<Term>

    suspend fun findById(id: Int): Term

    suspend fun saveTermComment(commentDto: CommentDto, authorization: Map<String, String>): Response<Void>

}