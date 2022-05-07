package com.example.upyourpartyandroid.ui.fragments.registration

import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects

sealed class RegistrationSideEffect : BaseSideEffects {

    object ShowSuccessRegistrationMessage: RegistrationSideEffect()

    object MailAddressInvalid : RegistrationSideEffect()

    object NameInvalid : RegistrationSideEffect()

    object SecondNameInvalid : RegistrationSideEffect()

    object LastNameInvalid : RegistrationSideEffect()

    object PasswordInvalid : RegistrationSideEffect()

    object CityInvalid : RegistrationSideEffect()

    object PasswordsMatchError : RegistrationSideEffect()

    object PasswordLengthError : RegistrationSideEffect()

}