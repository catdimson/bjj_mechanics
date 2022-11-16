package ru.catdimson.bjjmechanics.domain.entities.general

data class Contact(
    val id: Int,
    val type: ContactType,
    val value: String
) {
    override fun toString(): String {
        return "Contact(type=$type, value=$value)"
    }
}