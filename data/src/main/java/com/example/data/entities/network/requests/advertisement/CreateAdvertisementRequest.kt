package com.example.data.entities.network.requests.advertisement

import com.example.data.entities.network.advertisement.RemoteAdsProfile
import com.google.gson.annotations.SerializedName

class CreateAdvertisementRequest(
    @SerializedName("price")
    val price: String,
    @SerializedName("adsProfile")
    val adsProfile: RemoteAdsProfile,
    @SerializedName("city")
    val city: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("adsImages")
    val images: List<String>,
    @SerializedName("id")
    val id: Long? = null
)
