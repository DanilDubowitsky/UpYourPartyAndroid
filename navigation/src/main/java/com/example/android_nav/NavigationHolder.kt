package com.example.android_nav

import java.util.HashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationHolder @Inject constructor() {

    private val navigators: HashMap<String?, IAppNavigator?> = hashMapOf()

    private val resultListeners: HashMap<ResultKey<*>, ResultListener<*>> = hashMapOf()

    fun setupNavigator(navigator: IAppNavigator, key: String? = null) {
        this.navigators[key] = navigator
    }

    fun removeNavigator(key: String? = null) {
        this.navigators[key] = null
    }

    fun getNavigator(key: String?): IAppNavigator? = navigators[key]

    fun <T> sendResult(key: ResultKey<T>, data: T) {
        val listener = resultListeners.remove(key) as? ResultListener<T>
        listener?.onResult(data)
    }

    fun <T> setResultListener(resultKey: ResultKey<T>, listener: ResultListener<T>) {
        resultListeners[resultKey] = listener
    }

}