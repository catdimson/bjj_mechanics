package ru.catdimson.bjjmechanics.core.validations

class PasswordValidation(
    private val passwordValue: String
) : AbstractValidation(passwordValue) {

    val pattern = """^[A-Za-z\d\\_]{8,32}$""".toRegex()

    override fun isValid() = pattern.matches(passwordValue)

}