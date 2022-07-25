package com.example.domain.usecase.advertisement

import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetAdvertisement @Inject constructor(
    private val service: IService.IAdvertisementsService,
    private val repository: IRxRepositoryContract.IAdvertisementRepository
) : FlowableUseCase<Long, DomainFullAdvertisement>() {

    override fun createFlow(arguments: Long): Flowable<DomainFullAdvertisement> {
        this.syncAdvertisementData(arguments)
        return repository.getFullAdvertisement(arguments)
    }

    private fun syncAdvertisementData(id: Long) {
        service.getAdvertisement(id).flatMapCompletable { fullAdvertisement ->
            repository.addFullAdvertisement(fullAdvertisement)
        }.processIOCompletable().subscribe()
    }

    override fun release() {
        repository.release()
        super.release()
    }

}
