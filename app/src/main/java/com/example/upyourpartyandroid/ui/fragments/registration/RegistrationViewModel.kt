package com.example.upyourpartyandroid.ui.fragments.registration

import com.example.domain.entities.net.registration.DomainRegistrationProfile
import com.example.domain.entities.net.registration.DomainRegistration
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.Utils.formatPhoneToString
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import com.example.upyourpartyandroid.ui.fragments.validation.NameValidator
import com.example.upyourpartyandroid.ui.fragments.validation.PasswordValidator
import com.example.upyourpartyandroid.ui.fragments.validation.PasswordValidator.Companion.PASSWORD_MIN_LENGTH
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    dataSource: IRegistrationWorkGroup,
    private val nameValidator: NameValidator,
    private val passwordValidator: PasswordValidator,
    private val router: IRouter
) : BaseMVIViewModel<RegistrationState, IRegistrationWorkGroup>(
    dataSource
) {

    override fun createInitialState(): RegistrationState = RegistrationState()

    fun onBackPress() {
        router.back()
    }

    private fun validateFields(
        name: String,
        lastName: String,
        secondName: String,
        phone: String,
        mail: String,
        password: String,
        city: String,
        repeatedPassword: String,
        onSuccess: () -> Unit
    ) {
        var isValid = true

        if (!mail.contains("@")) {
            postSideEffect(RegistrationSideEffect.MailAddressInvalid)
            isValid = false
        }

        if (!nameValidator.validate(name)) {
            postSideEffect(RegistrationSideEffect.NameInvalid)
            isValid = false
        }

        if (!nameValidator.validate(lastName)) {
            postSideEffect(RegistrationSideEffect.LastNameInvalid)
            isValid = false
        }

        if (!nameValidator.validate(secondName)) {
            postSideEffect(RegistrationSideEffect.SecondNameInvalid)
            isValid = false
        }

        if (!passwordValidator.validate(password)) {
            postSideEffect(RegistrationSideEffect.PasswordInvalid)
            isValid = false
        }

        if(!nameValidator.validate(city)) {
            postSideEffect(RegistrationSideEffect.CityInvalid)
            isValid = false
        }

        if(password != repeatedPassword) {
            postSideEffect(RegistrationSideEffect.PasswordsMatchError)
            isValid = false
        }

        if(password.length < PASSWORD_MIN_LENGTH) {
            postSideEffect(RegistrationSideEffect.PasswordLengthError)
            isValid = false
        }

        if (isValid) onSuccess()
    }

    fun registerUser(
        name: String,
        lastName: String,
        secondName: String,
        phone: String,
        mail: String,
        password: String,
        repeatedPassword: String,
        city: String
    ) {
        val formattedName = name.trim()
        val formattedLastName = lastName.trim()
        val formattedPhone = phone.formatPhoneToString().trim()
        val formattedSecondName = secondName.trim()
        val formattedCity = city.trim()
        val formattedMail = mail.trim()
        val formattedPassword = password.trim()

        validateFields(
            formattedName,
            formattedLastName,
            formattedSecondName,
            formattedPhone,
            formattedMail,
            formattedPassword,
            formattedCity,
            repeatedPassword
        ) {
            val profilePerson = DomainRegistrationProfile(
                formattedName,
                formattedLastName,
                formattedPhone,
                formattedSecondName,
                formattedCity
            )
            val netRegistration = DomainRegistration(
                formattedMail,
                formattedPassword,
                profilePerson
            )
            dataSource.registerUseCase.invoke(netRegistration).handleSubscribe {
                postSideEffect(RegistrationSideEffect.ShowSuccessRegistrationMessage)
            }
        }
    }

}