package com.example.domain.service

import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainFullAdvertisement
import com.example.domain.entities.net.login.DomainLogin
import com.example.domain.entities.net.login.DomainRefresh
import com.example.domain.entities.net.registration.DomainRegistration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.io.File

interface IService {

    interface IAuthService : IService {

        fun registration(arguments: DomainRegistration): Completable

        fun login(arguments: DomainLogin): Single<Map<String, String>>

        fun refresh(arguments: DomainRefresh): Single<Map<String, String>>

    }

    interface IAdvertisementsService : IService {

        fun getMyAdvertisements(): Single<List<DomainAdvertisement>>

        fun uploadAdvertisementImage(
            file: File,
            mimeType: String,
            key: Any
        ): Single<Map<String, String>>

        fun createAdvertisement(
            price: String,
            description: String,
            city: String,
            category: String,
            title: String,
            images: List<String>
        ): Completable

        fun deleteAdvertisementsImages(
            images: List<String>
        ): Completable

        fun deleteAdvertisement(
            id: Long
        ): Completable

        fun changeAdvertisement(
            advertisementId: Long,
            price: String,
            description: String,
            city: String,
            category: String,
            title: String,
            images: List<String>
        ): Completable

        fun getAdvertisement(
            id: Long
        ): Single<DomainFullAdvertisement>

    }

}