package com.example.upyourpartyandroid.ui.fragments.login

import com.example.android_nav.NavigationScreen
import com.example.domain.enteties.net.login.DomainLogin
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.navigation.Router
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import com.example.upyourpartyandroid.ui.fragments.validation.PasswordValidator
import com.example.upyourpartyandroid.ui.fragments.validation.PasswordValidator.Companion.PASSWORD_MIN_LENGTH
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    dataSource: ILoginWorkGroup,
    private val passwordValidator: PasswordValidator,
    private val router: IRouter
) : BaseMVIViewModel<LoginState, ILoginWorkGroup>(
    dataSource
) {

    override fun createInitialState(): LoginState = LoginState()

    private fun validate(
        mail: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        var isValid = true

        if (!mail.contains("@")) {
            isValid = false
            postSideEffect(LoginSideEffect.MailValidationError)
        }

        if (password.length < PASSWORD_MIN_LENGTH) {
            isValid = false
            postSideEffect(LoginSideEffect.PasswordLengthError)
        }

        if (!passwordValidator.validate(password)) {
            isValid = false
            postSideEffect(LoginSideEffect.PasswordValidationError)
        }

        if (isValid) onSuccess()

    }

    fun login(
        mail: String,
        password: String
    ) {
        val formattedMail = mail.trim()
        val formattedPassword = password.trim()

        validate(formattedMail, formattedPassword) {
            val argument = DomainLogin(formattedMail, formattedPassword)
            dataSource.loginUseCase(argument).handleSubscribe {
                router.replace(NavigationScreen.Main.Home)
            }
        }
    }

    fun navigateToRegistration() {
        router.navigateTo(NavigationScreen.Auth.Registration)
    }

}