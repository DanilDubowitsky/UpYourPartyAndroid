package com.example.android_nav

fun interface ResultListener<T> {
    fun onResult(data: T)
}