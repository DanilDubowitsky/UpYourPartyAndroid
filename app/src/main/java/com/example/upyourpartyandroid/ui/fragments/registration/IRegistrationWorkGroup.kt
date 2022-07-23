package com.example.upyourpartyandroid.ui.fragments.registration

import com.example.domain.usecase.auth.Registrater
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IRegistrationWorkGroup : IRxWorkGroup {

    val register: Registrater

}