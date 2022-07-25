package com.example.data.entities.network.review

import com.example.data.entities.network.user.RemoteUser
import com.google.gson.annotations.SerializedName

data class RemoteReview(
    @SerializedName("id")
    val id: Long,
    @SerializedName("content")
    val content: String,
    @SerializedName("adsid")
    val advertisementId: Long,
    @SerializedName("authPerson")
    val user: RemoteUser,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("date")
    val date: String
)
