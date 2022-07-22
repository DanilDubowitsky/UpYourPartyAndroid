package com.example.data.converters

inline fun <reified E : Enum<E>> String.toEnumModel() = enumValueOf<E>(this)
