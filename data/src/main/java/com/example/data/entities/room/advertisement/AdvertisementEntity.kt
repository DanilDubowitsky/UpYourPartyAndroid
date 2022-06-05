package com.example.data.entities.room.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.enteties.advertisement.DomainAdvertisement

@Entity
data class AdvertisementEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val rating: Float,
    val price: Int,
    val city: String,
    val category: AdvertisementCategory,
    val description: String,
    val images: Collection<String>,
    val isFavorite: Boolean,
    val isMy: Boolean = false
)
