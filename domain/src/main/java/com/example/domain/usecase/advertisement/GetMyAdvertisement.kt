package com.example.domain.usecase.advertisement

import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.usecase.global.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMyAdvertisement @Inject constructor(
    private val advertisementsRepository: IRxRepositoryContract.IAdvertisementRepository
) : SingleUseCase<Long, DomainAdvertisement>() {

    override fun createFlow(arguments: Long): Single<DomainAdvertisement> {
        return advertisementsRepository.getAdvertisement(arguments)
    }

}
