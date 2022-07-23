package com.example.upyourpartyandroid.ui.fragments.profile

import com.example.android_nav.NavigationScreen
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    dataSource: IProfileWorkGroup,
    private val router: IRouter
) : BaseMVIViewModel<ProfileState, IProfileWorkGroup>(dataSource) {

    override fun createInitialState(): ProfileState = ProfileState()

    fun logOut() {
        dataSource.logOut(Unit).handleSubscribe {
            router.replace(NavigationScreen.Auth.Login)
        }
    }
}
