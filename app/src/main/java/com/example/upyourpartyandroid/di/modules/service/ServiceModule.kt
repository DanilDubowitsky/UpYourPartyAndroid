package com.example.upyourpartyandroid.di.modules.service

import com.example.data.service.advertisements.AdvertisementsService
import com.example.domain.service.IService
import com.example.data.service.auth.AuthService
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {

    @Binds
    abstract fun bindAuthService(service: AuthService): IService.IAuthService

    @Binds
    abstract fun bindAdvertisementsService(service: AdvertisementsService): IService.IAdvertisementsService

}