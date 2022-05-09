package com.example.android_nav

import com.example.navigation.screens.Screen

sealed interface Command {

    data class Forward(val screen: Screen) : Command

    data class BackTo(val screen: Screen?) : Command

    data class Replace(val screen: Screen) : Command

    object Back : Command

}