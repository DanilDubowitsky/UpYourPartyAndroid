package com.example.data.entities.network.profile

import com.google.gson.annotations.SerializedName

data class RemoteUserProfile(
    @SerializedName("name")
    val name: String,
    @SerializedName("middleName")
    val middleName: String,
    @SerializedName("numberPhone")
    val numberPhone: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("avatar")
    val avatar: String
)
