package com.example.domain.usecase.review

import com.example.domain.service.IService
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class SubmitReview @Inject constructor(
    private val reviewService: IService.IAdvertisementReviewService
) : CompletableUseCase<SubmitReview.Arguments>() {


    override fun createFlow(arguments: Arguments): Completable = arguments.run {
        reviewService.addReview(
            advertisementId,
            content,
            rating,
            date
        )
    }

    class Arguments(
        val advertisementId: Long,
        val content: String,
        val rating: String,
        val date: String
    )
}