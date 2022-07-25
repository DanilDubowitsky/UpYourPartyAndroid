package com.example.domain.usecase.auth

import com.example.domain.model.net.login.DomainLogin
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class Login @Inject constructor(
    private val authService: IService.IAuthService,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : CompletableUseCase<DomainLogin>() {

    override fun createFlow(arguments: DomainLogin): Completable {
        return authService.login(arguments).map { result ->
            userPreferences.email = arguments.email
            userPreferences.authToken = "Bearer_" + result[AUTH_TOKEN_KEY]
            userPreferences.refreshToken = "Bearer_" + result[AUTH_REFRESH_TOKEN_KEY]
            userPreferences.userId = result[AUTH_USER_ID_KEY]!!.toLong()
            userPreferences.lastTokenUpdate = System.currentTimeMillis()
        }.ignoreElement()
    }

    companion object {
        const val AUTH_TOKEN_KEY = "token"
        const val AUTH_REFRESH_TOKEN_KEY = "token_refresh"
        const val AUTH_USER_ID_KEY = "userId"
    }

}