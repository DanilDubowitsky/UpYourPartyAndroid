package com.example.data.entities.network.requests.advertisement

import com.google.gson.annotations.SerializedName

class AdvertisementSearchRequest(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("sort")
    val sort: String = "",
    @SerializedName("city")
    val city: String = "",
    @SerializedName("category")
    val category: String = ""
)
