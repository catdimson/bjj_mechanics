package ru.catdimson.bjjmechanics.domain.datasource

interface DataSource<T> {

    suspend fun getData(): T

}