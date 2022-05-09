package com.example.android_nav

import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationHolder @Inject constructor() {

    private val navigators: HashMap<String?, IAppNavigator?> = hashMapOf()

    fun setupNavigator(navigator: IAppNavigator, key: String? = null) {
        this.navigators[key] = navigator
    }

    fun removeNavigator(key: String? = null) {
        this.navigators[key] = null
    }

    fun getNavigator(key: String?): IAppNavigator? = navigators[key]

}