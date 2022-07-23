package com.example.data.entities.network.profile

import com.google.gson.annotations.SerializedName

class RemoteProfile(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Long
)
