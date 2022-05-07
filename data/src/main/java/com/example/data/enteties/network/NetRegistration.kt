package com.example.data.enteties.network

import com.google.gson.annotations.SerializedName

class NetRegistration(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("profilePerson")
    val netProfile: NetProfile
)