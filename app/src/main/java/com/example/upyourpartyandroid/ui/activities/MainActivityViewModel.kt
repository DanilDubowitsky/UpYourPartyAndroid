package com.example.upyourpartyandroid.ui.activities

import androidx.lifecycle.ViewModel
import com.example.android_nav.NavigationScreen
import com.example.upyourpartyandroid.navigation.IRouter
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: IRouter
) : ViewModel() {

    fun navigateToHome() {
        router.replace(NavigationScreen.Main.Home)
    }

    fun navigateToLogin() {
        router.replace(NavigationScreen.Auth.Login)
    }

}