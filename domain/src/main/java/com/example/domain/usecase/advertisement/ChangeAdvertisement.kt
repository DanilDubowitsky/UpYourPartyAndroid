package com.example.domain.usecase.advertisement

import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class ChangeAdvertisement @Inject constructor(
    private val service: IService.IAdvertisementsService,
    private val repository: IRxRepositoryContract.IAdvertisementRepository
) : CompletableUseCase<ChangeAdvertisement.Arguments>() {

    override fun createFlow(arguments: Arguments): Completable = arguments.run {
        service.changeAdvertisement(
            advertisementId,
            price,
            description,
            city,
            category.name,
            title,
            images
        )
    }

    class Arguments(
        val advertisementId: Long,
        val price: String,
        val description: String,
        val city: String,
        val category: DomainAdvertisementCategory,
        val title: String,
        val images: List<String>
    )
}
