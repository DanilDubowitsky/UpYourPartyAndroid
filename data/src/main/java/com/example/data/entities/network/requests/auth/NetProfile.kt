package com.example.data.entities.network.requests.auth

import com.google.gson.annotations.SerializedName

class NetProfile(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val lastName: String,
    @SerializedName("numberPhone")
    val numberPhone: String,
    @SerializedName("middleName")
    val secondName: String,
    @SerializedName("city")
    val city: String
)