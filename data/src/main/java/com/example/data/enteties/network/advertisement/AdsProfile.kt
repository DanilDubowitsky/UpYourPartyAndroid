package com.example.data.enteties.network.advertisement

import com.google.gson.annotations.SerializedName

data class AdsProfile(
    @SerializedName("description")
    private val description: String
)