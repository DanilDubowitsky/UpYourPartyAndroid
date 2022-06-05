package com.example.data.entities.network.advertisement

import com.google.gson.annotations.SerializedName

data class RemoteAdsProfile(
    @SerializedName("description")
    val description: String
)