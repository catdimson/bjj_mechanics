package ru.catdimson.bjjmechanics.domain.datasource.interactor.terms

import ru.catdimson.bjjmechanics.domain.entities.terms.Term

interface TermsInteractor {

    suspend fun findAll(authMap: Map<String, String>): List<Term>

    suspend fun findById(id: Int, authMap: Map<String, String>): Term

}