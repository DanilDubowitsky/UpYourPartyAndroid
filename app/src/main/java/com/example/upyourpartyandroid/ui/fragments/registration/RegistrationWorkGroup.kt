package com.example.upyourpartyandroid.ui.fragments.registration

import com.example.domain.usecase.auth.RegistrationUseCase
import javax.inject.Inject

class RegistrationWorkGroup @Inject constructor(
    override val registerUseCase: RegistrationUseCase
) : IRegistrationWorkGroup {

    override fun release() {
        registerUseCase.release()
    }

}