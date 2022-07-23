package com.example.data.converters.remote

import com.example.data.converters.toEnumModel
import com.example.data.entities.network.advertisement.RemoteAdsImage
import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.advertisement.RemoteFullAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainFullAdvertisement

fun RemoteAdvertisement.toModel(
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
    attachments.toModels(serverApiUrl),
    isFavorite = myFavorite != 0L,
    isMy = userData.id == currentUserId
)

fun RemoteFullAdvertisement.toModel(currentUserId: Long, serverApiUrl: String) = DomainFullAdvertisement(
    id,
    email,
    phoneNumber,
    title,
    description,
    price,
    city,
    category.toEnumModel(),
    rating,
    imageRemotes.toModels(serverApiUrl),
    isFavorite = isFavorite != 0L,
    isMy = currentUserId == userId
)

private fun List<RemoteAdsImage>.toModels(serverApiUrl: String) = this.map { adsImage ->
    if (adsImage.filename.isNotBlank()) serverApiUrl + adsImage.filename
    else ""
}
