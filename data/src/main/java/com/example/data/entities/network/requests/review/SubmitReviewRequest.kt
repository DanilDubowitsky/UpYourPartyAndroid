package com.example.data.entities.network.requests.review

import com.google.gson.annotations.SerializedName

data class SubmitReviewRequest(
    @SerializedName("adsid")
    val advertisementId: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("date")
    val date: String
)
