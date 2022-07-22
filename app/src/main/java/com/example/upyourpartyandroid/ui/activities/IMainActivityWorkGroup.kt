package com.example.upyourpartyandroid.ui.activities

import com.example.domain.usecase.auth.RefreshTokenUseCase

interface IMainActivityWorkGroup {
    val refreshTokenUseCase: RefreshTokenUseCase
}
