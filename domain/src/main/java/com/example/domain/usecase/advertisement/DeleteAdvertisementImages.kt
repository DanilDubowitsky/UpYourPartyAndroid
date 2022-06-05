package com.example.domain.usecase.advertisement

import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class DeleteAdvertisementImages @Inject constructor(
    private val advertisementService: IService.IAdvertisementsService
) : CompletableUseCase<List<String>>() {

    override fun createFlow(arguments: List<String>): Completable {
        return advertisementService.deleteAdvertisementsImages(arguments)
    }

}
