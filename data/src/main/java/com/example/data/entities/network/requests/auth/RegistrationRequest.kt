package com.example.data.entities.network.requests.auth

import com.example.data.entities.network.requests.auth.NetProfile
import com.google.gson.annotations.SerializedName

class RegistrationRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("profilePerson")
    val netProfile: NetProfile
)