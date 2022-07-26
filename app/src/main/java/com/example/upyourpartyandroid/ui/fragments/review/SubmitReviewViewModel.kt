package com.example.upyourpartyandroid.ui.fragments.review

import android.icu.text.SimpleDateFormat
import com.example.domain.usecase.review.SubmitReview
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.DateUtils.REVIEW_DATE_FORMAT
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import java.util.Locale
import javax.inject.Inject

class SubmitReviewViewModel @Inject constructor(
    dataSource: ISubmitReviewWorkGroup,
    private val router: IRouter
) : BaseMVIViewModel<SubmitReviewState, ISubmitReviewWorkGroup>(dataSource) {

    override fun createInitialState(): SubmitReviewState = SubmitReviewState(
        DEFAULT_ADVERTISEMENT_ID, DEFAULT_RATING
    )

    fun prepare(advertisementId: Long) = reduce {
        copy(advertisementId = advertisementId)
    }

    fun onRatingChange(newRating: Float) = reduce {
        copy(selectedRating = newRating)
    }

    fun submitReview(rating: Float, comment: String) {
        val dateMillis = System.currentTimeMillis()
        val dateFormatter = SimpleDateFormat(REVIEW_DATE_FORMAT, Locale.getDefault())
        val formattedDate = dateFormatter.format(dateMillis)
        val arguments = SubmitReview.Arguments(
            currentState.advertisementId,
            comment,
            rating.toInt().toString(),
            formattedDate
        )
        dataSource.submitReview(arguments).handleSubscribe {
            router.back()
        }
    }

    fun onBackClick() {
        router.back()
    }

    companion object {
        const val DEFAULT_RATING = 4f
        const val DEFAULT_ADVERTISEMENT_ID = 0L
    }

}