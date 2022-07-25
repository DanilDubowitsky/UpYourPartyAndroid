package com.example.data.service.auth

import com.example.data.NetworkApi
import com.example.data.entities.network.requests.auth.LoginRequest
import com.example.data.entities.network.requests.auth.NetProfile
import com.example.data.entities.network.requests.auth.RefreshTokenRequest
import com.example.data.entities.network.requests.auth.RegistrationRequest
import com.example.domain.model.net.login.DomainLogin
import com.example.domain.model.net.login.DomainRefresh
import com.example.domain.service.IService
import com.example.domain.model.net.registration.DomainRegistration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthService @Inject constructor(
    private val retrofitApi: NetworkApi
) : IService.IAuthService {

    override fun registration(arguments: DomainRegistration): Completable {
        val request = arguments.run {
            RegistrationRequest(
                email,
                password,
                NetProfile(
                    profilePerson.name,
                    profilePerson.lastName,
                    profilePerson.numberPhone,
                    profilePerson.secondName,
                    profilePerson.city
                )
            )
        }

        return retrofitApi.registration(request)
    }

    override fun login(arguments: DomainLogin): Single<Map<String, String>> {
        val request = arguments.run {
            LoginRequest(email, password)
        }

        return retrofitApi.login(request)
    }

    override fun refresh(arguments: DomainRefresh): Single<Map<String, String>> {
        val request = arguments.run {
            RefreshTokenRequest(
                email,
                refreshToken
            )
        }

        return retrofitApi.getRefreshToken(request)
    }

}