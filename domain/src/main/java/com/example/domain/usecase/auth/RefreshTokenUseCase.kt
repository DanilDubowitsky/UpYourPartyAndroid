package com.example.domain.usecase.auth

import com.example.domain.enteties.net.login.DomainRefresh
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import com.example.domain.usecase.auth.LoginUseCase.Companion.AUTH_REFRESH_TOKEN_KEY
import com.example.domain.usecase.auth.LoginUseCase.Companion.AUTH_TOKEN_KEY
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val authService: IService.IAuthService,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : CompletableUseCase<DomainRefresh>() {

    override fun createFlow(arguments: DomainRefresh): Completable {
        return authService.refresh(arguments).map { response ->
            userPreferences.authToken = "Bearer_" + response[AUTH_TOKEN_KEY]
            userPreferences.refreshToken = response[AUTH_REFRESH_TOKEN_KEY]
        }.ignoreElement()
    }

}