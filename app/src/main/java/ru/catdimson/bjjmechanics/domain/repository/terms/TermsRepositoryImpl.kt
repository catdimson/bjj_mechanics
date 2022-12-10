package ru.catdimson.bjjmechanics.domain.repository.terms

import retrofit2.Response
import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.dto.terms.CommentDto

class TermsRepositoryImpl(
    private val dataSource: DataSource
) : TermsRepository {

    override suspend fun findAll(authMap: Map<String, String>): List<Term> {
        return dataSource.findAllTerms(authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): Term {
        return dataSource.findTermById(id, authMap)
    }

    override suspend fun saveTermComment(
        commentDto: CommentDto,
        tokens: Map<String, String>
    ): Response<Void> {
        return dataSource.saveTermComment(commentDto, tokens)
    }
}