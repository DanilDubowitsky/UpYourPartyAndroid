package com.example.data.enteties.network.advertisement

import com.google.gson.annotations.SerializedName

data class NetAdvertisement(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("title")
    private val title: String,
    @SerializedName("price")
    private val price: String,
    @SerializedName("city")
    private val city: String,
    @SerializedName("category")
    private val category: String,
    @SerializedName("attachments")
    private val attachments: List<AdsImage>,
    @SerializedName("myFavorite")
    private val myFavorite: Long,
    @SerializedName("adsProfile")
    private val adsProfile: AdsProfile,
)