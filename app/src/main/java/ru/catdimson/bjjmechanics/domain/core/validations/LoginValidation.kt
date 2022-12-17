package ru.catdimson.bjjmechanics.domain.core.validations

class LoginValidation(
    private val loginValue: String
) : Validation {

    val pattern = """^[A-Za-z\d\\_]{4,32}$""".toRegex()

    override fun isValid() = pattern.matches(loginValue)

}