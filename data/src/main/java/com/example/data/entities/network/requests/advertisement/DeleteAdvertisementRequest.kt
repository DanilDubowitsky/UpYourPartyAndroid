package com.example.data.entities.network.requests.advertisement

import com.google.gson.annotations.SerializedName

class DeleteAdvertisementRequest(
    @SerializedName("id")
    val id: Long
)
