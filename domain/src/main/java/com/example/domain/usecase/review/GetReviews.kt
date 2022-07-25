package com.example.domain.usecase.review

import com.example.domain.model.review.Review
import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.service.IService
import com.example.domain.usecase.global.FlowableUseCase
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class GetReviews @Inject constructor(
    private val service: IService.IAdvertisementReviewService,
    private val reviewRepository: IRxRepositoryContract.IReviewRepository,
    private val userRepository: IRxRepositoryContract.IUserRepository
) : FlowableUseCase<Long, List<Review>>() {

    override fun createFlow(arguments: Long): Flowable<List<Review>> {
        this.syncReviews(arguments)
        return reviewRepository.getReviews(arguments)
    }

    private fun syncReviews(argument: Long) {
        service.getAdvertisementReviews(argument).concatMapCompletable { reviews ->
            val users = reviews.map(Review::user)
            userRepository.addUsers(users)
            reviewRepository.addReviews(reviews)
        }.processIOCompletable().subscribe()
    }

    override fun release() {
        reviewRepository.release()
        userRepository.release()
        super.release()
    }
}
