package com.example.domain.service

import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.model.net.login.DomainLogin
import com.example.domain.model.net.login.DomainRefresh
import com.example.domain.model.net.registration.DomainRegistration
import com.example.domain.model.review.Review
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

        fun changeFavoriteStatus(
            id: Long,
            isFavorite: Boolean
        ): Completable

        fun getAdvertisements(
            category: DomainAdvertisementCategory,
            title: String,
            sort: String,
            city: String
        ): Single<List<DomainAdvertisement>>
    }

    interface IAdvertisementReviewService : IService {

        fun getAdvertisementReviews(
            advertisementId: Long
        ): Single<List<Review>>

        fun addReview(
            advertisementId: Long,
            content: String,
            rating: String,
            date: String
        ): Completable
    }

}