package com.example.upyourpartyandroid.ui.activities

import com.example.domain.usecase.auth.RefreshToken
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup
import javax.inject.Inject

class MainActivityWorkGroup @Inject constructor(
    override val refreshToken: RefreshToken
) : IRxWorkGroup, IMainActivityWorkGroup {

    override fun release() {
        refreshToken.release()
    }
}
