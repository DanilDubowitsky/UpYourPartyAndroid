package com.example.upyourpartyandroid.navigation

import android.os.Handler
import android.os.Looper
import com.example.android_nav.Command
import com.example.android_nav.NavigationHolder
import com.example.android_nav.NavigationScreen
import javax.inject.Inject

class Router @Inject constructor(
    private val screenCreator: ScreenCreator,
    private val navigationHolder: NavigationHolder
) : IRouter {

    private val mainHandler: Handler = Handler(Looper.getMainLooper())

    override fun navigateTo(screen: NavigationScreen, key: String?) {
        val navScreen = screenCreator.createScreen(screen)
        applyCommand(Command.Forward(navScreen), key)
    }

    override fun replace(screen: NavigationScreen, key: String?) {
        val navScreen = screenCreator.createScreen(screen)
        applyCommand(Command.Replace(navScreen), key)
    }

    override fun back() {
        applyCommand(Command.Back)
    }

    private fun applyCommand(command: Command, key: String? = null) {
        val navigator = navigationHolder.getNavigator(key)
        mainHandler.post {
            navigator?.applyCommand(command)
        }
    }

}