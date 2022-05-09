package com.example.android_nav

interface Command {

    data class Forward(val screen: Screen): Command

    data class Replace(val screen: Screen): Command

    object Back : Command

}