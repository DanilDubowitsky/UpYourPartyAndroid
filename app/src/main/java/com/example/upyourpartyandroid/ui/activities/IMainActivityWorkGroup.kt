package com.example.upyourpartyandroid.ui.activities

import com.example.domain.usecase.auth.RefreshToken

interface IMainActivityWorkGroup {
    val refreshToken: RefreshToken
}
