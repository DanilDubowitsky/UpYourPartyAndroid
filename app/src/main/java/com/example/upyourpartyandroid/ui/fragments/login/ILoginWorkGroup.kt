package com.example.upyourpartyandroid.ui.fragments.login

import com.example.domain.usecase.auth.Login
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface ILoginWorkGroup : IRxWorkGroup {
    val login: Login
}