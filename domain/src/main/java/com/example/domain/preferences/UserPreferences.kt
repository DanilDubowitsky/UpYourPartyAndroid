package com.example.domain.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferences @Inject constructor(
    preferences: SharedPreferences
) : BaseSharedPreferences(preferences), IPreferencesContract.IUserPreferences {

    override var authToken: String by PreferencesString()

    override var name: String by PreferencesString()

    override var secondName: String by PreferencesString()

    override var lastName: String by PreferencesString()

    override var phone: String by PreferencesString()

    override var email: String by PreferencesString()

    override var profileImage: String by PreferencesString()

    companion object {
        const val USER_PREFS_NAME = "user_preferences_name"
    }

}