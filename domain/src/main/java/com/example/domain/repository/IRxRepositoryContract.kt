package com.example.domain.repository

import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.entities.advertisement.DomainFullAdvertisement
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IRxRepositoryContract {

    interface IRxRepository {
        fun release()
    }

    interface IAdvertisementRepository : IRxRepository {

        fun getMyAdvertisements(): Flowable<List<DomainAdvertisement>>

        fun getAllAdvertisements(category: String): Flowable<List<DomainAdvertisement>>

        fun add(advertisement: DomainAdvertisement): Completable

        fun addAll(items: List<DomainAdvertisement>): Completable

        fun deleteAllAdvertisements(category: DomainAdvertisementCategory): Completable

        fun getFullAdvertisement(id: Long): Single<DomainFullAdvertisement>

        fun getAdvertisement(id: Long): Single<DomainAdvertisement>

        fun deleteAdvertisement(id: Long): Completable

        fun changeAdvertisement(
            advertisementId: Long,
            price: String,
            description: String,
            city: String,
            category: String,
            title: String,
            images: List<String>
        ): Completable

    }

}