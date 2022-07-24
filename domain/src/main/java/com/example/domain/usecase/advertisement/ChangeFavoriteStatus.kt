package com.example.domain.usecase.advertisement

import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class ChangeFavoriteStatus @Inject constructor(
    private val service: IService.IAdvertisementsService,
    private val repository: IRxRepositoryContract.IAdvertisementRepository
) : CompletableUseCase<ChangeFavoriteStatus.Arguments>() {

    override fun createFlow(arguments: Arguments): Completable {
        return service.changeFavoriteStatus(arguments.id, arguments.boolean).andThen(
            repository.changeFavoriteStatus(arguments.id, arguments.boolean)
        )
    }

    class Arguments(
        val id: Long,
        val boolean: Boolean
    )
}