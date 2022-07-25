package com.example.domain.usecase.advertisement

import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetAdvertisements @Inject constructor(
    private val advertisementsRepository: IRxRepositoryContract.IAdvertisementRepository,
    private val advertisementsService: IService.IAdvertisementsService
) : FlowableUseCase<GetAdvertisements.Arguments, List<DomainAdvertisement>>() {

    override fun createFlow(arguments: Arguments): Flowable<List<DomainAdvertisement>> =
        arguments.run {
            syncAdvertisements(arguments)
            return advertisementsRepository.getAllAdvertisements(
                category,
                title,
                sort,
                city
            )
        }

    private fun syncAdvertisements(arguments: Arguments) {
        advertisementsService.getAdvertisements(
            arguments.category,
            arguments.title,
            arguments.sort,
            arguments.city
        ).flatMapCompletable { advertisements ->
            advertisementsRepository.addAll(advertisements)
        }.processIOCompletable().subscribe()
    }

    override fun release() {
        advertisementsRepository.release()
        super.release()
    }

    class Arguments(
        val category: DomainAdvertisementCategory,
        val title: String,
        val sort: String,
        val city: String
    )
}