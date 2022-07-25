package com.example.domain.model.review

import com.example.domain.model.user.User

data class Review(
    val id: Long,
    val content: String,
    val advertisementId: Long,
    val user: User,
    val rating: String,
    val date: String
)
