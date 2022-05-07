package com.example.domain.repository

import com.example.domain.enteties.DomainAdvertisement
import io.reactivex.rxjava3.core.Flowable

interface IRxRepositoryContract {

    interface IAdvertisementRepository {

        fun getAllAdvertisements(category: String): Flowable<List<DomainAdvertisement>>

    }

}