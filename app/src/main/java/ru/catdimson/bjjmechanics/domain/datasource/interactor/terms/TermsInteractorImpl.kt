package ru.catdimson.bjjmechanics.domain.datasource.interactor.terms

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepository
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

class TermsInteractorImpl(
    private val repository: TermsRepository
) : TermsInteractor {

    override suspend fun findAll(authMap: Map<String, String>): List<Term> {
        return repository.findAll(authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): Term {
        return repository.findById(id, authMap)
    }

    override suspend fun saveTermComment(
        commentDto: CommentDto,
        tokens: Map<String, String>
    ): Response<Void> {
        return repository.saveTermComment(commentDto, tokens)
    }

}