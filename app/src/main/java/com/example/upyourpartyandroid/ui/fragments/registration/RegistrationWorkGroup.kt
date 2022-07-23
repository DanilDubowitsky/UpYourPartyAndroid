package com.example.upyourpartyandroid.ui.fragments.registration

import com.example.domain.usecase.auth.Registrater
import javax.inject.Inject

class RegistrationWorkGroup @Inject constructor(
    override val register: Registrater
) : IRegistrationWorkGroup {

    override fun release() {
        register.release()
    }

}