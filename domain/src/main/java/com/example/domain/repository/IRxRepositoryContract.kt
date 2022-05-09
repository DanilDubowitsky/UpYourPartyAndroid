package com.example.domain.repository

import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IRxRepositoryContract {

    interface IRxRepository {
        fun release()
    }

    interface IAdvertisementRepository : IRxRepository {

        fun getAllAdvertisements(category: String): Flowable<List<DomainAdvertisement>>

    }

    interface IMyAdvertisementsRepository : IRxRepository {

        fun getMyAdvertisements(): Flowable<List<DomainFullAdvertisement>>

        fun getById(id: Long): Single<DomainFullAdvertisement>

        fun delete(id: Long): Completable

        fun add(advertisement: DomainFullAdvertisement): Completable

        fun addAll(items: List<DomainFullAdvertisement>): Completable

        fun deleteAll(): Completable

    }

}