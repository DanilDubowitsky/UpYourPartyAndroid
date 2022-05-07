package com.example.data.enteties.network

import com.google.gson.annotations.SerializedName

class NetLogin(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)