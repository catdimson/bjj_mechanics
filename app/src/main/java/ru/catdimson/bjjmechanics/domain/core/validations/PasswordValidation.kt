package ru.catdimson.bjjmechanics.domain.core.validations

class PasswordValidation(
    private val passwordValue: String
) : Validation {

    val pattern = """^[A-Za-z\d\\_]{8,32}$""".toRegex()

    override fun isValid() = pattern.matches(passwordValue)

}