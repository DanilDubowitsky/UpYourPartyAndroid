package com.example.domain.preferences

interface IPreferencesContract {

    interface IPreferences {
        fun clear()
    }

    interface IUserPreferences : IPreferences {
        var authToken: String
        var name: String
        var secondName: String
        var lastName: String
        var phone: String
        var email: String
        var profileImage: String
    }

}