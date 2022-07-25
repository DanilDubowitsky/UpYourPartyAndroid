package com.example.data.service.review

import com.example.data.NetworkApi
import com.example.data.converters.remote.toModels
import com.example.data.entities.network.requests.review.SubmitReviewRequest
import com.example.data.entities.network.review.RemoteReview
import com.example.domain.model.review.Review
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdvertisementReviewService @Inject constructor(
    private val api: NetworkApi,
    private val userPreferences: IPreferencesContract.IUserPreferences,
    private val imageApiUrl: String
) : IService.IAdvertisementReviewService {

    override fun getAdvertisementReviews(advertisementId: Long): Single<List<Review>> {
        val token = userPreferences.authToken ?: throw Exception("user is not authorized")
        return api.getAdvertisementReviews(advertisementId, token).map { reviews ->
            reviews.toModels(imageApiUrl)
        }
    }

    override fun addReview(
        advertisementId: Long,
        content: String,
        rating: String,
        date: String
    ): Completable {
        val token = userPreferences.authToken ?: throw Exception("user is not authorized")
        val request = SubmitReviewRequest(
            advertisementId,
            content,
            rating,
            date
        )
        return api.setReview(request, token)
    }
}
