package com.example.domain.usecase.advertisement

import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class GetMyAdvertisementsUseCase @Inject constructor(
    private val myAdvertisementsRepository: IRxRepositoryContract.IAdvertisementRepository,
    private val advertisementsService: IService.IAdvertisementsService
) : FlowableUseCase<Unit, List<DomainAdvertisement>>() {

    private var serviceDisposable: Disposable? = null

    override fun createFlow(arguments: Unit): Flowable<List<DomainAdvertisement>> {
        syncAdvertisements()
        return myAdvertisementsRepository.getMyAdvertisements()
            .processIOFlowable()
    }

    private fun syncAdvertisements() {
        serviceDisposable = advertisementsService.getMyAdvertisements()
            .flatMapCompletable { advertisements ->
                myAdvertisementsRepository.addAll(advertisements)
            }.processIOCompletable().subscribe()
    }

    override fun release() {
        myAdvertisementsRepository.release()
        serviceDisposable?.dispose()
        super.release()
    }

}