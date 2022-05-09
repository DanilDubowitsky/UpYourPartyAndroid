package com.example.upyourpartyandroid.navigation

import com.example.android_nav.NavigationScreen
import com.example.android_nav.Screen

interface IRouter {

    fun navigateTo(screen: NavigationScreen, key: String? = null)

    fun replace(screen: NavigationScreen, key: String? = null)

    fun back()

}