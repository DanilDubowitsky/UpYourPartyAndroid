package com.example.data.service.auth

import com.example.data.NetworkApi
import com.example.data.enteties.network.NetLogin
import com.example.data.enteties.network.NetProfile
import com.example.data.enteties.network.NetRegistration
import com.example.domain.enteties.net.login.DomainLogin
import com.example.domain.service.IService
import com.example.domain.enteties.net.registration.DomainRegistration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthService @Inject constructor(
    private val retrofitApi: NetworkApi
) : IService.IAuthService {

    override fun registration(arguments: DomainRegistration): Completable {
        val request = arguments.run {
            NetRegistration(
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
            NetLogin(email, password)
        }

        return retrofitApi.login(request)
    }

}