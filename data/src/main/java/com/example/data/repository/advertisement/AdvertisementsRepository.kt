package com.example.data.repository.advertisement

import com.example.data.dao.AdvertisementDao
import com.example.data.enteties.room.advertisement.Advertisement
import com.example.domain.RxDataSource
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class AdvertisementsRepository @Inject constructor(
    private val dao: AdvertisementDao
) : RxDataSource(), IRxRepositoryContract.IAdvertisementRepository {

    override fun getAllAdvertisements(category: String): Flowable<List<DomainAdvertisement>> {
        return dao.getAllAdvertisement(category).map { advertisementList ->
            advertisementList.map(Advertisement::toDomain)
        }.processIOFlowable()
    }

}