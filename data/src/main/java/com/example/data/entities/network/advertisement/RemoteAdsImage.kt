package com.example.data.entities.network.advertisement

import com.google.gson.annotations.SerializedName

data class RemoteAdsImage(
    @SerializedName("filename")
    val filename: String
)