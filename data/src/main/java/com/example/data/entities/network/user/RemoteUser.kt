package com.example.data.entities.network.user

import com.example.data.entities.network.profile.RemoteUserProfile
import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("profilePerson")
    val userProfile: RemoteUserProfile
)
