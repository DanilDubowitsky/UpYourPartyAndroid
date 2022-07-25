package com.example.data.converters.local

import com.example.data.entities.room.user.UserEntity
import com.example.data.entities.room.user.UserProfileLocal
import com.example.domain.model.user.User
import com.example.domain.model.user.UserProfile

fun UserEntity.toModel() = User(
    id,
    email,
    userProfile.toModel()
)

fun UserProfileLocal.toModel() = UserProfile(
    name,
    middleName,
    numberPhone,
    surname,
    city,
    avatar
)

fun User.toEntity() = UserEntity(
    id,
    email,
    userProfile.toLocal()
)

fun UserProfile.toLocal() = UserProfileLocal(
    name,
    middleName,
    numberPhone,
    surname,
    city,
    avatar
)

fun List<User>.toEntities() = this.map(User::toEntity)
