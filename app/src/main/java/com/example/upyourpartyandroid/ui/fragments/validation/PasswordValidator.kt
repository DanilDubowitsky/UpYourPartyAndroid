package com.example.upyourpartyandroid.ui.fragments.validation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordValidator @Inject constructor() {

    private val regex = Regex("^[a-zA-Z0-9]+\$")

    fun validate(validationString: String): Boolean {
        return regex.matches(validationString)
    }

    companion object {
        const val PASSWORD_MIN_LENGTH = 6
    }

}