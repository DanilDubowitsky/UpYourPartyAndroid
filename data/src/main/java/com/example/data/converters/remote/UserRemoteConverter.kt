package com.example.data.converters.remote

import com.example.data.entities.network.user.RemoteUser
import com.example.data.entities.network.profile.RemoteUserProfile
import com.example.domain.model.user.User
import com.example.domain.model.user.UserProfile

fun RemoteUser.toModel(avatarUrl: String) = User(
    id,
    email,
    userProfile.toModel(avatarUrl)
)

private fun RemoteUserProfile.toModel(
    avatarUrl: String
) = UserProfile(
    name,
    middleName,
    numberPhone,
    surname,
    city,
    if (!avatar.isNullOrBlank()) avatarUrl + avatar
    else ""
)
