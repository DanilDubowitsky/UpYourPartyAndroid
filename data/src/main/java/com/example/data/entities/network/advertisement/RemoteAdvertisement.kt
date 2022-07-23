package com.example.data.entities.network.advertisement

import com.example.data.entities.network.profile.RemoteProfile
import com.google.gson.annotations.SerializedName

data class RemoteAdvertisement(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("price")
    val price: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("attachments")
    val attachments: List<RemoteAdsImage>,
    @SerializedName("myFavorite")
    val myFavorite: Long,
    @SerializedName("adsProfile")
    val adsProfile: RemoteAdsProfile,
    @SerializedName("authPerson")
    val userData: RemoteProfile
)