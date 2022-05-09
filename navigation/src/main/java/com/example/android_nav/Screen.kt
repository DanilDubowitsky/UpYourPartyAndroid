package com.example.android_nav

interface Screen {
    val screenKey: String get() = this::class.java.name
}