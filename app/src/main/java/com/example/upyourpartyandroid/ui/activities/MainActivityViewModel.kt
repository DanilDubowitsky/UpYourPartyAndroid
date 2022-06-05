package com.example.upyourpartyandroid.ui.activities

import androidx.lifecycle.ViewModel
import com.example.android_nav.NavigationScreen
import com.example.domain.preferences.IPreferencesContract
import com.example.upyourpartyandroid.navigation.IRouter
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: IRouter,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : ViewModel() {

    fun checkIsAuthorize() {
        val token = userPreferences.authToken
        router.replace(NavigationScreen.Auth.Login)
//        if(token != null) router.replace(NavigationScreen.Main.Home)
//        else router.replace(NavigationScreen.Auth.Login)
    }

}