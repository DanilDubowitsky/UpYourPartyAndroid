package com.example.data.entities.room.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FullAdvertisementEntity(
    @PrimaryKey
    val id: Long,
    val email: String,
    val phoneNumber: String,
    val title: String,
    val description: String,
    val price: Int,
    val city: String,
    val category: String,
    val rating: Float,
    val images: List<String>,
    val isFavorite: Boolean,
    val isMy: Boolean
)
