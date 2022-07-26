package com.example.upyourpartyandroid.ui.fragments.review

import com.example.domain.usecase.review.SubmitReview
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface ISubmitReviewWorkGroup : IRxWorkGroup {
    val submitReview: SubmitReview
}