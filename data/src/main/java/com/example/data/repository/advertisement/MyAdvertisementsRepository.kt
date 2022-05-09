package com.example.data.repository.advertisement

import com.example.data.dao.MyAdvertisementsDao
import com.example.data.enteties.room.advertisement.FullAdvertisement
import com.example.domain.RxDataSource
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MyAdvertisementsRepository @Inject constructor(
    private val dao: MyAdvertisementsDao
) : RxDataSource(), IRxRepositoryContract.IMyAdvertisementsRepository {

    override fun getMyAdvertisements(): Flowable<List<DomainFullAdvertisement>> =
        dao.getAll().map { items ->
            items.map(FullAdvertisement::toDomain)
        }.processIOFlowable()


    override fun getById(id: Long): Single<DomainFullAdvertisement> =
        dao.getById(id).map(FullAdvertisement::toDomain)
            .processIOSingle()

    override fun delete(id: Long): Completable =
        dao.delete(id)
            .processIOCompletable()

    override fun add(advertisement: DomainFullAdvertisement): Completable {
        val dataItem = FullAdvertisement.toEntity(advertisement)
        return dao.add(dataItem)
            .processIOCompletable()
    }

    override fun addAll(items: List<DomainFullAdvertisement>): Completable {
        val dataItem = items.map(FullAdvertisement::toEntity)
        return dao.addAll(dataItem)
            .processIOCompletable()
    }

    override fun deleteAll(): Completable = dao.deleteAll()
        .processIOCompletable()

}