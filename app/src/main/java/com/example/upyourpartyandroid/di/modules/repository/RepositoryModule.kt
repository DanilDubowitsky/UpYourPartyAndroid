package com.example.upyourpartyandroid.di.modules.repository

import com.example.data.repository.advertisement.AdvertisementsRepository
import com.example.domain.repository.IRxRepositoryContract
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAdvertisementRepository(repository: AdvertisementsRepository): IRxRepositoryContract.IAdvertisementRepository

}