package com.example.upyourpartyandroid.ui.fragments.registration

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentRegistrationBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.showError
import com.redmadrobot.inputmask.MaskedTextChangedListener

class RegistrationFragment :
    BaseRequestFragment<FragmentRegistrationBinding, RegistrationViewModel>(
        RegistrationViewModel::class,
        FragmentRegistrationBinding::inflate
    ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupListeners()
        this.setupViews()
        viewModel.observe(this, ::handleState, ::handleSideEffect)
    }

    private fun handleState(state: RegistrationState) {}

    private fun setupViews() {
        MaskedTextChangedListener.installOn(binding.editTextPhone, RUSSIAN_MASK_FORMAT)
    }

    private fun handleSideEffect(sideEffect: BaseSideEffects) {
        when(sideEffect) {

            is RegistrationSideEffect.ShowSuccessRegistrationMessage -> {
                val message = getString(R.string.registration_success_message)
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }

            is BaseSideEffects.ShowMessage -> {
                Toast.makeText(requireContext(), sideEffect.message, Toast.LENGTH_LONG).show()
            }

            is RegistrationSideEffect.SecondNameInvalid -> {
                binding.editTextSecondName.showError(R.string.bio_validation_error)
            }

            is RegistrationSideEffect.NameInvalid -> {
                binding.editTextName.showError(R.string.bio_validation_error)
            }

            is RegistrationSideEffect.LastNameInvalid -> {
                binding.editTextLastName.showError(R.string.bio_validation_error)
            }

            is RegistrationSideEffect.MailAddressInvalid -> {
                binding.editTextMail.showError(R.string.mail_address_validation_error)
            }

            is RegistrationSideEffect.PasswordInvalid -> {
                binding.editTextPassword.showError(R.string.password_validation_error)
            }

            is RegistrationSideEffect.CityInvalid -> {
                binding.editTextCity.showError(R.string.bio_validation_error)
            }

            is RegistrationSideEffect.PasswordsMatchError -> {
                binding.editTextConfirmPassword.showError(R.string.password_match_error)
            }

            is RegistrationSideEffect.PasswordLengthError -> {
                binding.editTextPassword.showError(R.string.password_length_error)
            }

        }
    }

    private fun setupListeners() {
        binding.backBtn.setClickListener(viewModel::onBackPress)
        binding.btnRegistration.setClickListener(::onRegisterButtonClick)
    }

    private fun onRegisterButtonClick() = with(binding) {
        val name = editTextName.text.toString()
        val lastName = editTextLastName.text.toString()
        val secondName = editTextSecondName.text.toString()
        val password = editTextPassword.text.toString()
        val city = editTextCity.text.toString()
        val phone = editTextPhone.text.toString()
        val mail = editTextMail.text.toString()
        val confirmationPassword = editTextConfirmPassword.text.toString()

        viewModel.registerUser(
            name,
            lastName,
            secondName,
            phone,
            mail,
            password,
            confirmationPassword,
            city
        )
    }

    companion object {
        const val RUSSIAN_MASK_FORMAT = "+7 ([000]) [000]-[00]-[00]"
    }

}