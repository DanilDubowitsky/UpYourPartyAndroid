package com.example.data.converters.remote

import com.example.data.converters.toEnumModel
import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.advertisement.RemoteFullAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainFullAdvertisement

fun RemoteAdvertisement.toDomain(
    currentUserId: Long,
    serverApiUrl: String
) = DomainAdvertisement(
    id,
    title,
    rating,
    price,
    city,
    category.toEnumModel(),
    adsProfile.description,
    attachments.map { adsImage ->
        if (adsImage.filename.isNotBlank()) serverApiUrl + adsImage.filename
        else ""
    },
    isFavorite = myFavorite != 0L,
    isMy = userData.id == currentUserId
)

fun RemoteFullAdvertisement.toDomain(currentUserId: Long) = DomainFullAdvertisement(
    id,
    email,
    phoneNumber,
    title,
    description,
    price,
    city,
    category.toEnumModel(),
    rating,
    isFavorite = isFavorite != 0L,
    isMy = currentUserId == userId
)
