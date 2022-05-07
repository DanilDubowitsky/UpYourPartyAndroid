package com.example.domain.usecase.auth

import com.example.domain.enteties.net.login.DomainLogin
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authService: IService.IAuthService,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : CompletableUseCase<DomainLogin>() {

    override fun createFlow(arguments: DomainLogin): Completable {
        return authService.login(arguments).map { result ->
            userPreferences.authToken = "Bearer_" + result[AUTH_TOKEN_KEY]
        }.ignoreElement()
    }

    companion object {
        const val AUTH_TOKEN_KEY = "token"
    }

}