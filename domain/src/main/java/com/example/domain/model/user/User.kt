package com.example.domain.model.user

data class User(
    val id: Long,
    val email: String,
    val userProfile: UserProfile
)
