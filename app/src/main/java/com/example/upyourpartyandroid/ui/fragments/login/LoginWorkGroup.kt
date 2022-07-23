package com.example.upyourpartyandroid.ui.fragments.login

import com.example.domain.usecase.auth.Login
import javax.inject.Inject

class LoginWorkGroup @Inject constructor(
    override val login: Login
) : ILoginWorkGroup {

    override fun release() {
        login.release()
    }

}