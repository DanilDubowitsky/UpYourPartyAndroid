package com.example.domain.usecase.advertisement

import com.example.domain.enteties.DomainAdvertisement
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.usecase.global.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetAllAdvertisementsSingleUseCase @Inject constructor(
    private val advertisementsRepository: IRxRepositoryContract.IAdvertisementRepository
) : FlowableUseCase<GetAllAdvertisementsSingleUseCase.Arguments, List<DomainAdvertisement>>() {

    override fun createFlow(arguments: Arguments): Flowable<List<DomainAdvertisement>> {
        return advertisementsRepository.getAllAdvertisements(arguments.category)
    }

    class Arguments(
        val category: String
    )

}