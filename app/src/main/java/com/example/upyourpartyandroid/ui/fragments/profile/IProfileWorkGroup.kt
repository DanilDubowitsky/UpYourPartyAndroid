package com.example.upyourpartyandroid.ui.fragments.profile

import com.example.domain.usecase.auth.LogOut
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IProfileWorkGroup : IRxWorkGroup {
    val logOut: LogOut
}