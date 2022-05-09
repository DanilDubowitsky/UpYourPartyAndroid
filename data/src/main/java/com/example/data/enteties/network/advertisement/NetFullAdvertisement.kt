package com.example.data.enteties.network.advertisement

import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.google.gson.annotations.SerializedName

data class NetFullAdvertisement(
    @SerializedName("id")
    val id: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("number_phone")
    val phoneNumber: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("rating")
    val rating: String
) {

    fun toDomain(): DomainFullAdvertisement =
        DomainFullAdvertisement(
            id,
            email,
            phoneNumber,
            title,
            description,
            price,
            city,
            category,
            rating
        )

}