package com.example.upyourpartyandroid.ui.fragments.login

import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects

sealed class LoginSideEffect : BaseSideEffects {

    object MailValidationError : LoginSideEffect()

    object PasswordValidationError : LoginSideEffect()

    object PasswordLengthError : LoginSideEffect()

    object NavigateToHomeFragment : LoginSideEffect()

}