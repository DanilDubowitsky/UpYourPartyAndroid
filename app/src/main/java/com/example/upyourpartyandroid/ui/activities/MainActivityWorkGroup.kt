package com.example.upyourpartyandroid.ui.activities

import com.example.domain.usecase.auth.RefreshTokenUseCase
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup
import javax.inject.Inject

class MainActivityWorkGroup @Inject constructor(
    override val refreshTokenUseCase: RefreshTokenUseCase
) : IRxWorkGroup, IMainActivityWorkGroup {

    override fun release() {
        refreshTokenUseCase.release()
    }
}
