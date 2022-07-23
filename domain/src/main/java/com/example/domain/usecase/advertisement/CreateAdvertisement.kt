package com.example.domain.usecase.advertisement

import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class CreateAdvertisement @Inject constructor(
    private val advertisementsService: IService.IAdvertisementsService
) : CompletableUseCase<CreateAdvertisement.Arguments>() {

    override fun createFlow(arguments: Arguments): Completable {
        return arguments.run {
            advertisementsService.createAdvertisement(
                price,
                description,
                city,
                category,
                title,
                images
            )
        }
    }

    class Arguments(
        val price: String,
        val description: String,
        val city: String,
        val category: String,
        val title: String,
        val images: List<String>
    )

}
