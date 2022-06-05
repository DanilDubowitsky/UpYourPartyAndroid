package com.example.data.converters.remote

import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.advertisement.RemoteFullAdvertisement
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.enteties.advertisement.DomainAdvertisementCategory
import com.example.domain.enteties.advertisement.DomainFullAdvertisement

fun RemoteAdvertisement.toDomain(
    currentUserId: Long,
    serverApiUrl: String
) = DomainAdvertisement(
    id,
    title,
    rating,
    price,
    city,
    DomainAdvertisementCategory.valueOf(category),
    adsProfile.description,
    attachments.map { adsImage ->
        serverApiUrl + adsImage.filename
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
    DomainAdvertisementCategory.valueOf(category),
    rating,
    isFavorite = isFavorite != 0L,
    isMy = currentUserId == userId
)
