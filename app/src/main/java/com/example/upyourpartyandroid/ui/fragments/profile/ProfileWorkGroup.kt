package com.example.upyourpartyandroid.ui.fragments.profile

import com.example.domain.usecase.auth.LogOut
import javax.inject.Inject

class ProfileWorkGroup @Inject constructor(
    override val logOut: LogOut
) : IProfileWorkGroup {

    override fun release() {
        logOut.release()
    }
}
