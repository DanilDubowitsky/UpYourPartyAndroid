package com.example.data.entities.room.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val email: String,
    @Embedded
    val userProfile: UserProfileLocal
)
