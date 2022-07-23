package com.example.domain.usecase.auth

import com.example.domain.entities.net.registration.DomainRegistration
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class Registrater @Inject constructor(
    private val authService: IService.IAuthService
) : CompletableUseCase<DomainRegistration>() {

    override fun createFlow(arguments: DomainRegistration): Completable {
        return authService.registration(arguments)
    }

}