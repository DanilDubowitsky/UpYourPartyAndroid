package com.example.upyourpartyandroid.ui.fragments.login

import com.example.domain.usecase.auth.LoginUseCase
import javax.inject.Inject

class LoginWorkGroup @Inject constructor(
    override val loginUseCase: LoginUseCase
) : ILoginWorkGroup {

    override fun release() {
        loginUseCase.release()
    }

}