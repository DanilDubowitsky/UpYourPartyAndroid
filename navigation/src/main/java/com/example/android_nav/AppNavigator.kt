package com.example.android_nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager

class AppNavigator @JvmOverloads constructor(
    private val activity: FragmentActivity,
    private val containerId: Int,
    private val fragmentManager: FragmentManager = activity.supportFragmentManager,
    private val fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory,
    private var initialFragment: Fragment? = null
) : IAppNavigator {

    init {
        if (initialFragment != null) replace(Command.Replace(
            FragmentScreen {
                initialFragment!!
            }
        )
        )
        initialFragment = null
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is Command.Forward -> forward(command)
            is Command.Replace -> replace(command)
            is Command.Back -> back()
        }
    }

    private fun back() {
        fragmentManager.popBackStack()
    }

    private fun forward(command: Command.Forward) {
        when (command.screen) {
            is FragmentScreen -> createForForwardFragment(command.screen)
            is DialogScreen -> createForForwardDialog(command.screen)
        }
    }

    private fun createForForwardDialog(screen: DialogScreen) {
        val dialog = screen.createDialog(fragmentFactory)
        dialog.show(fragmentManager, screen.screenKey)
    }

    private fun createForForwardFragment(screen: FragmentScreen) {
        val fragment = screen.createFragment(fragmentFactory)
        fragmentManager.beginTransaction()
            .addToBackStack(screen.screenKey)
            .replace(containerId, fragment, screen.screenKey)
            .commit()
    }

    private fun replace(command: Command.Replace) {
        when (command.screen) {
            is FragmentScreen -> createForReplaceFragment(command.screen)
        }
    }

    private fun createForReplaceFragment(screen: FragmentScreen) {
        val fragment = screen.createFragment(fragmentFactory)
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, screen.screenKey)
            .commit()
    }

}