package com.example.upyourpartyandroid.ui.fragments.validation

import javax.inject.Inject

class NameValidator @Inject constructor() {

    private val regex = Regex("^[а-яА-Я]+\$")

    fun validate(validationString: String): Boolean {
        return regex.matches(validationString)
    }

}