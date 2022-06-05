package com.example.data.entities.network.advertisement

import com.google.gson.annotations.SerializedName

data class RemoteFullAdvertisement(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("number_phone")
    val phoneNumber: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("adsImages")
    val imageRemotes: List<RemoteAdsImage>,
    @SerializedName("myFavorite")
    val isFavorite: Long,
    @SerializedName("userId")
    val userId: Long
)
