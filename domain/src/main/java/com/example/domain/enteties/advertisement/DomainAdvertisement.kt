package com.example.domain.enteties.advertisement

data class DomainAdvertisement(
    val id: Long,
    val title: String,
    val rating: Float,
    val price: Int,
    val city: String,
    val category: String,
    val description: String,
    val images: List<String>,
    val isFavorite: Boolean
)