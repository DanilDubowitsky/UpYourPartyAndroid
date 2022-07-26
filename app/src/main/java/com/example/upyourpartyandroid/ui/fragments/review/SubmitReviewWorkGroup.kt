package com.example.upyourpartyandroid.ui.fragments.review

import com.example.domain.usecase.review.SubmitReview
import javax.inject.Inject

class SubmitReviewWorkGroup @Inject constructor(
    override val submitReview: SubmitReview
) : ISubmitReviewWorkGroup {

    override fun release() {
        submitReview.release()
    }
}