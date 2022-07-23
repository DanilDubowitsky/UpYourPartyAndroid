package com.example.android_nav

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

fun interface Creator<A, R> {
    fun create(argument: A): R
}

interface FragmentScreen : Screen {
    val clearContainer: Boolean get() = true
    fun createFragment(factory: FragmentFactory): Fragment
    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, Fragment>
        ): Screen = object : FragmentScreen {
            override val screenKey: String = key ?: fragmentCreator::class.java.name
            override val clearContainer: Boolean = clearContainer
            override fun createFragment(factory: FragmentFactory): Fragment = fragmentCreator.create(factory)
        }
    }
}

interface DialogScreen : Screen {
    val clearContainer: Boolean get() = true
    fun createDialog(factory: FragmentFactory): DialogFragment
    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, DialogFragment>
        ): Screen = object : DialogScreen {
            override val screenKey: String = key ?: fragmentCreator::class.java.name
            override val clearContainer: Boolean = clearContainer
            override fun createDialog(factory: FragmentFactory): DialogFragment = fragmentCreator.create(factory)
        }
    }
}
