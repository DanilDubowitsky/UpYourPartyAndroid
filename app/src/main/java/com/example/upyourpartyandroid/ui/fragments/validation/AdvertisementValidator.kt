package com.example.upyourpartyandroid.ui.fragments.validation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvertisementValidator @Inject constructor() {

    private val regex = Regex("[^a-zA-Z0-9_\\s-_`‘’']")

    fun validate(validationString: String): Boolean {
        return regex.matches(validationString)
    }

}