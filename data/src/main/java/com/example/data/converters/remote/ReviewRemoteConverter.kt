package com.example.data.converters.remote

import com.example.data.entities.network.review.RemoteReview
import com.example.domain.model.review.Review

fun RemoteReview.toModel(avatarUrl: String) = Review(
    id,
    content,
    advertisementId,
    user.toModel(avatarUrl),
    rating,
    date
)

fun List<RemoteReview>.toModels(avatarUrl: String) = this.map { review ->
    review.toModel(avatarUrl)
}
