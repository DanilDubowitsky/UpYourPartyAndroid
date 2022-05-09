package com.example.domain.enteties.advertisement

data class DomainFullAdvertisement(
    val id: Long,
    val email: String,
    val phoneNumber: String,
    val title: String,
    val description: String,
    val price: Int,
    val city: String,
    val category: String,
    val rating: String
)