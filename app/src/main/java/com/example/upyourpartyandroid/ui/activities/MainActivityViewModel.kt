package com.example.upyourpartyandroid.ui.activities

import androidx.lifecycle.ViewModel
import com.example.android_nav.NavigationScreen
import com.example.domain.entities.net.login.DomainRefresh
import com.example.domain.preferences.IPreferencesContract
import com.example.upyourpartyandroid.navigation.IRouter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: IRouter,
    private val dataSource: IMainActivityWorkGroup,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : ViewModel() {

    fun checkIsAuthorize() {
        val lastTokenTimeUpdate = userPreferences.lastTokenUpdate
        if (userPreferences.authToken.isNullOrBlank()) {
            router.replace(NavigationScreen.Auth.Login)
            return
        }
        checkTokenUpdateTime(lastTokenTimeUpdate)
    }

    private fun checkTokenUpdateTime(lastTokenTimeUpdate: Long) {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastTokenTimeUpdate) >= HOUR_IN_MILLIS) {
            val arguments = DomainRefresh(
                userPreferences.email,
                userPreferences.refreshToken!!
            )
            dataSource.refreshTokenUseCase(arguments)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        router.replace(NavigationScreen.Main.Home)
                    }, {
                        router.replace(NavigationScreen.Auth.Login)
                    })
        } else {
            router.replace(NavigationScreen.Main.Home)
        }
    }

    companion object {
        const val HOUR_IN_MILLIS = 86400000L
    }

}