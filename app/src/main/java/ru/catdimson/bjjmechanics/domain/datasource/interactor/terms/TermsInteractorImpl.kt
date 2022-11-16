package ru.catdimson.bjjmechanics.domain.datasource.interactor.terms

import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.repository.terms.TermsRepository

class TermsInteractorImpl(
    private val repository: TermsRepository
) : TermsInteractor {

    override suspend fun findAll(authMap: Map<String, String>): List<Term> {
        return repository.findAll(authMap)
    }

    override suspend fun findById(id: Int, authMap: Map<String, String>): Term {
        return repository.findById(id, authMap)
    }

}