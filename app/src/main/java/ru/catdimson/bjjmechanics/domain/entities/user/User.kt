package ru.catdimson.bjjmechanics.domain.entities.user

data class User(
    var id: Int?,
    var login: String?,
    var password: String?,
    var role: Role?,
    var isActive: Int?,
    var person: Person?
)