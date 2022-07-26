package com.example.upyourpartyandroid.ui.fragments.validation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NameValidator @Inject constructor() {

    private val regex = Regex("^[а-яА-Я_\\s_-]+\$")

    fun validate(validationString: String): Boolean {
        return regex.matches(validationString)
    }

}