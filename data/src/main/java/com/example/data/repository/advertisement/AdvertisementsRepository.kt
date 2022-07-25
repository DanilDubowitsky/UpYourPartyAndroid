package com.example.data.repository.advertisement

import com.example.data.converters.local.toDomain
import com.example.data.converters.local.toEntity
import com.example.data.converters.local.toListEntities
import com.example.data.dao.AdvertisementDao
import com.example.data.entities.room.advertisement.AdvertisementEntity
import com.example.data.entities.room.advertisement.FullAdvertisementEntity
import com.example.domain.RxDataSource
import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdvertisementsRepository @Inject constructor(
    private val advertisementDao: AdvertisementDao
) : RxDataSource(), IRxRepositoryContract.IAdvertisementRepository {

    override fun getMyAdvertisements(): Flowable<List<DomainAdvertisement>> {
        return advertisementDao.getMyAdvertisements()
            .map(List<AdvertisementEntity>::toDomain)
            .processIOFlowable()
    }

    override fun getAllAdvertisements(
        category: DomainAdvertisementCategory,
        title: String,
        sort: String,
        city: String
    ): Flowable<List<DomainAdvertisement>> =
        advertisementDao.getAllAdvertisement(category.name).map { advertisementList ->
            advertisementList.map(AdvertisementEntity::toDomain)
        }.processIOFlowable()


    override fun add(advertisement: DomainAdvertisement): Completable =
        advertisementDao.insertOrUpdate(advertisement.toEntity())
            .processIOCompletable()


    override fun addAll(items: List<DomainAdvertisement>): Completable =
        advertisementDao.insertOrUpdateAdvertisements(items.map(DomainAdvertisement::toEntity))
            .processIOCompletable()


    override fun addAllFullAdvertisements(items: List<DomainFullAdvertisement>): Completable {
        val entities = items.toListEntities()
        return advertisementDao.insertOrUpdateFullAdvertisements(entities).processIOCompletable()
    }

    override fun deleteAllAdvertisements(category: DomainAdvertisementCategory): Completable = Completable.create { emitter ->
        advertisementDao.deleteAll(category.name)
        emitter.onComplete()
    }.processIOCompletable()

    override fun getFullAdvertisement(id: Long): Flowable<DomainFullAdvertisement> =
        advertisementDao.getFullAdvertisement(id).map(FullAdvertisementEntity::toDomain)
            .processIOFlowable()


    override fun getAdvertisement(id: Long): Single<DomainAdvertisement> =
        advertisementDao.getAdvertisement(id)
            .map(AdvertisementEntity::toDomain)
            .processIOSingle()

    override fun deleteAdvertisement(id: Long): Completable = Completable.create { emitter ->
        advertisementDao.deleteAdvertisement(id)
        emitter.onComplete()
    }.processIOCompletable()

    override fun changeAdvertisement(
        advertisementId: Long,
        price: String,
        description: String,
        city: String,
        category: String,
        title: String,
        images: List<String>
    ): Completable = Completable.create { emitter ->
        advertisementDao.changeAdvertisement(
            advertisementId,
            price,
            description,
            city,
            category,
            title,
            images
        )
        emitter.onComplete()
    }.processIOCompletable()


    override fun addFullAdvertisement(item: DomainFullAdvertisement): Completable {
        val fullAdvertisementEntity = item.toEntity()
        return advertisementDao.insertOrUpdate(fullAdvertisementEntity)
    }

    override fun changeFavoriteStatus(id: Long, isFavorite: Boolean): Completable =
        Completable.create { emitter ->
            advertisementDao.changeFavoriteStatus(id, isFavorite)
            emitter.onComplete()
        }.processIOCompletable()

}
