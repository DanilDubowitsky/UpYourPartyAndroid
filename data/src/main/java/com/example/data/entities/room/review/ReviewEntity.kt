package com.example.data.entities.room.review

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReviewEntity(
    @PrimaryKey
    val id: Long,
    val content: String,
    val advertisementId: Long,
    val userId: Long,
    val rating: String,
    val date: String
)
