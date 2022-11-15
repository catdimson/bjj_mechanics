package ru.catdimson.bjjmechanics.domain.repository.terms

import ru.catdimson.bjjmechanics.domain.datasource.DataSource
import ru.catdimson.bjjmechanics.domain.entities.terms.Term

class TermsRepositoryImpl(
    private val dataSource: DataSource
): TermsRepository  {

    override suspend fun findAll(authMap: Map<String, String>): List<Term> {
        return dataSource.findAllTerms(authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): Term {
        return dataSource.findTermById(id, authMap)
    }
}