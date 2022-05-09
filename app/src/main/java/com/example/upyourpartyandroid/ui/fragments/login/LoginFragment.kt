package com.example.upyourpartyandroid.ui.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.upyourpartyandroid.R
import com.example.upyourpartyandroid.databinding.FragmentLoginBinding
import com.example.upyourpartyandroid.ui.fragments.base.BaseFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseRequestFragment
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.fragments.home.HomeFragment
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationFragment
import com.example.upyourpartyandroid.ui.views.ViewUtils.setClickListener
import com.example.upyourpartyandroid.ui.views.ViewUtils.showError
import com.example.upyourpartyandroid.ui.views.ViewUtils.tryChangeVisibility
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : BaseRequestFragment<FragmentLoginBinding, LoginViewModel>(
    LoginViewModel::class,
    FragmentLoginBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(this, ::onStateChange, ::onSideEffect)
        setupListeners()
    }

    private fun setupListeners() {
        binding.registrationText.setClickListener(::onRegistrationClick)
        binding.btnLogin.setClickListener(::onLoginClick)
    }

    private fun onLoginClick() = with(binding) {
        val email = mailEditText.text.toString()
        val password = passwordEditText.text.toString()
        viewModel.login(email, password)
    }

    private fun onStateChange(state: LoginState) {}

    private fun onSideEffect(sideEffect: BaseSideEffects) {

        when(sideEffect) {

            is BaseSideEffects.ShowMessage -> showSnackBar(sideEffect.message)

            is BaseSideEffects.HideLoadingIndicator -> hideLoadingIndicator()

            is BaseSideEffects.ShowLoadingIndicator -> showLoadingIndicator()

            is LoginSideEffect.MailValidationError -> {
                binding.mailEditText.showError(R.string.mail_address_validation_error)
            }

            is LoginSideEffect.PasswordValidationError -> {
                binding.passwordEditText.showError(R.string.password_validation_error)
            }

            is LoginSideEffect.PasswordLengthError -> {
                binding.passwordEditText.showError(R.string.password_length_error)
            }

        }

    }

    private fun onRegistrationClick() {
        viewModel.navigateToRegistration()
    }

}