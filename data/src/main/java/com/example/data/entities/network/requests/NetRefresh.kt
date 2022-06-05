package com.example.data.entities.network.requests

import com.google.gson.annotations.SerializedName

class NetRefresh(
    @SerializedName("email")
    val email: String,
    @SerializedName("refreshToken")
    val refresh: String
)