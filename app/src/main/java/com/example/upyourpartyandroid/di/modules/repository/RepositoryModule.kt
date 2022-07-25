package com.example.upyourpartyandroid.di.modules.repository

import com.example.data.repository.advertisement.AdvertisementsRepository
import com.example.data.repository.review.ReviewRepository
import com.example.data.repository.user.UserRepository
import com.example.domain.repository.IRxRepositoryContract
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAdvertisementRepository(repository: AdvertisementsRepository): IRxRepositoryContract.IAdvertisementRepository

    @Binds
    abstract fun bondReviewRepository(repository: ReviewRepository): IRxRepositoryContract.IReviewRepository

    @Binds
    abstract fun bindUserRepository(repository: UserRepository): IRxRepositoryContract.IUserRepository

}