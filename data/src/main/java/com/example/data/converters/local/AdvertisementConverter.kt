package com.example.data.converters.local

import com.example.data.converters.toEnumModel
import com.example.data.entities.room.advertisement.AdvertisementEntity
import com.example.data.entities.room.advertisement.FullAdvertisementEntity
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.enteties.advertisement.DomainAdvertisementCategory
import com.example.domain.enteties.advertisement.DomainFullAdvertisement

fun AdvertisementEntity.toDomain(): DomainAdvertisement =
    DomainAdvertisement(
        id,
        title,
        rating,
        price,
        city,
        category.toEnumModel(),
        description,
        images.toList(),
        isFavorite,
        isMy
    )

fun List<AdvertisementEntity>.toDomain() = this.map(AdvertisementEntity::toDomain)

fun FullAdvertisementEntity.toDomain() =
    DomainFullAdvertisement(
        id,
        email,
        phoneNumber,
        title,
        description,
        price,
        city,
        category.toEnumModel(),
        rating,
        isFavorite,
        isMy
    )

fun List<FullAdvertisementEntity>.toListDomain() = this.map(FullAdvertisementEntity::toDomain)

fun DomainFullAdvertisement.toEntity() = FullAdvertisementEntity(
    id,
    email,
    phoneNumber,
    title,
    description,
    price,
    city,
    category.name,
    rating,
    isFavorite,
    isMy
)

fun DomainAdvertisement.toEntity() = AdvertisementEntity(
    id,
    title,
    rating,
    price,
    city,
    category.name,
    description,
    images,
    isFavorite,
    isMy
)
