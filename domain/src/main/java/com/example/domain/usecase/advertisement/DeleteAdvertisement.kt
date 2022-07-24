package com.example.domain.usecase.advertisement

import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteAdvertisement @Inject constructor(
    private val advertisementService: IService.IAdvertisementsService,
    private val repository: IRxRepositoryContract.IAdvertisementRepository
) : CompletableUseCase<Long>() {

    override fun createFlow(arguments: Long): Completable {
        return advertisementService.deleteAdvertisement(arguments).andThen(
            repository.deleteAdvertisement(arguments)
        )
    }

    override fun release() {
        repository.release()
        super.release()
    }

}
