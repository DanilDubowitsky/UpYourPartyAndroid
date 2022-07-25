package com.example.domain.repository

import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.model.review.Review
import com.example.domain.model.user.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IRxRepositoryContract {

    interface IRxRepository {
        fun release()
    }

    interface IAdvertisementRepository : IRxRepository {

        fun getMyAdvertisements(): Flowable<List<DomainAdvertisement>>

        fun getAllAdvertisements(
            category: DomainAdvertisementCategory,
            title: String,
            sort: String,
            city: String
        ): Flowable<List<DomainAdvertisement>>

        fun add(advertisement: DomainAdvertisement): Completable

        fun addAll(items: List<DomainAdvertisement>): Completable

        fun addAllFullAdvertisements(items: List<DomainFullAdvertisement>): Completable

        fun deleteAllAdvertisements(category: DomainAdvertisementCategory): Completable

        fun getFullAdvertisement(id: Long): Flowable<DomainFullAdvertisement>

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

        fun addFullAdvertisement(item: DomainFullAdvertisement): Completable

        fun changeFavoriteStatus(id: Long, isFavorite: Boolean): Completable

    }

    interface IReviewRepository : IRxRepository {
        fun addReview(review: Review): Completable
        fun addReviews(reviews: List<Review>): Completable
        fun getReviews(advertisementId: Long): Flowable<List<Review>>
    }

    interface IUserRepository : IRxRepository {
        fun addUser(user: User): Completable
        fun addUsers(users: List<User>): Completable
    }

}