package com.example.upyourpartyandroid.navigation

import com.example.android_nav.NavigationScreen
import com.example.android_nav.ResultKey
import com.example.android_nav.ResultListener

interface IRouter {

    fun navigateTo(screen: NavigationScreen, key: String? = null)

    fun replace(screen: NavigationScreen, key: String? = null)

    fun back()

    fun <T> sendResult(key: ResultKey<T>, data: T)

    fun <T> setResultListener(resultKey: ResultKey<T>, listener: ResultListener<T>)

}