package com.example.data.converters.local

import com.example.data.entities.room.advertisement.AdvertisementCategory
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
        category.toDomain(),
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
        category.toDomain(),
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
    category.toLocal(),
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
    category.toLocal(),
    description,
    images,
    isFavorite,
    isMy
)

fun DomainAdvertisementCategory.toLocal() = when (this) {
    DomainAdvertisementCategory.BIRTHDAY -> AdvertisementCategory.BIRTHDAY
    DomainAdvertisementCategory.WEDDING -> AdvertisementCategory.WEDDING
    DomainAdvertisementCategory.CORPORATE -> AdvertisementCategory.CORPORATE
    DomainAdvertisementCategory.PARTY -> AdvertisementCategory.PARTY
}

fun AdvertisementCategory.toDomain() = when (this) {
    AdvertisementCategory.BIRTHDAY -> DomainAdvertisementCategory.BIRTHDAY
    AdvertisementCategory.WEDDING -> DomainAdvertisementCategory.WEDDING
    AdvertisementCategory.CORPORATE -> DomainAdvertisementCategory.CORPORATE
    AdvertisementCategory.PARTY -> DomainAdvertisementCategory.PARTY
}
