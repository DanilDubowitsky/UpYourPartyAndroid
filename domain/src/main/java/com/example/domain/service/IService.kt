package com.example.domain.service

import com.example.domain.enteties.net.login.DomainLogin
import com.example.domain.enteties.net.registration.DomainRegistration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IService {

    interface IAuthService : IService {

        fun registration(arguments: DomainRegistration): Completable

        fun login(arguments: DomainLogin): Single<Map<String, String>>

    }

}