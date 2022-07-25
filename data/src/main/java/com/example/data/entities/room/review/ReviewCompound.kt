package com.example.data.entities.room.review

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.entities.room.user.UserEntity

data class ReviewCompound(
    @Embedded
    val reviewEntity: ReviewEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: UserEntity
)
