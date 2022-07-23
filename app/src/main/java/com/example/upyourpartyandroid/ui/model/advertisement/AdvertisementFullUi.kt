package com.example.upyourpartyandroid.ui.model.advertisement

data class AdvertisementFullUi(
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
