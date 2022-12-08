package ru.catdimson.bjjmechanics.core.validations

class LoginValidation(
    private val loginValue: String
) : AbstractValidation(loginValue) {

    val pattern = """^[A-Za-z\d\\_]{4,32}$""".toRegex()

    override fun isValid() = pattern.matches(loginValue)

}