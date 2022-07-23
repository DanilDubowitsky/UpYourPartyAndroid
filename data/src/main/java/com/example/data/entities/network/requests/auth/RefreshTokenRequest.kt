package com.example.data.entities.network.requests.auth

import com.google.gson.annotations.SerializedName

class RefreshTokenRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("refreshToken")
    val refresh: String
)