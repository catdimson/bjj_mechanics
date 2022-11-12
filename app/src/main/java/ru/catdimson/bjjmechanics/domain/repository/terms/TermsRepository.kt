package ru.catdimson.bjjmechanics.domain.repository.terms

import ru.catdimson.bjjmechanics.domain.entities.terms.Term
import ru.catdimson.bjjmechanics.domain.repository.Repository

/**
 * Репозиторий для работы с Терминами
 * */
interface TermsRepository : Repository {

    suspend fun findAll(authMap: Map<String, String>): List<Term>

    suspend fun findById(id: Int): Term

}