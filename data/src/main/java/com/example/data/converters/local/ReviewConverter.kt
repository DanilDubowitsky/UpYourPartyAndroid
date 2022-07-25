package com.example.data.converters.local

import com.example.data.entities.room.review.ReviewCompound
import com.example.data.entities.room.review.ReviewEntity
import com.example.domain.model.review.Review

fun Review.toEntity() = ReviewEntity(
    id,
    content,
    advertisementId,
    user.id,
    rating,
    date
)

fun List<Review>.toEntities() = this.map(Review::toEntity)

fun ReviewCompound.toModel() = Review(
    reviewEntity.id,
    reviewEntity.content,
    reviewEntity.advertisementId,
    user.toModel(),
    reviewEntity.rating,
    reviewEntity.date
)

fun List<ReviewCompound>.toModels() = this.map(ReviewCompound::toModel)
