package com.example.data.repository.review

import com.example.data.converters.local.toEntities
import com.example.data.converters.local.toEntity
import com.example.data.converters.local.toModels
import com.example.data.dao.ReviewDao
import com.example.data.entities.room.review.ReviewCompound
import com.example.domain.RxDataSource
import com.example.domain.model.review.Review
import com.example.domain.repository.IRxRepositoryContract
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val dao: ReviewDao
) : RxDataSource(), IRxRepositoryContract.IReviewRepository {

    override fun addReview(review: Review): Completable =
        dao.insertOrUpdate(review.toEntity())
            .processIOCompletable()

    override fun addReviews(reviews: List<Review>): Completable =
        dao.insertOrUpdate(reviews.toEntities())
            .processIOCompletable()

    override fun getReviews(advertisementId: Long, limit: Int): Flowable<List<Review>> =
        dao.getReviews(advertisementId, limit).map(List<ReviewCompound>::toModels)
            .processIOFlowable()
}
