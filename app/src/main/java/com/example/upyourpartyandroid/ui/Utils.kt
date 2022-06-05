package com.example.upyourpartyandroid.ui

object Utils {

    fun String.formatPhoneToString(): String {
        return replace("(", "")
            .replace(")", "")
            .replace(" ", "")
            .replace("-", "")
    }

}
