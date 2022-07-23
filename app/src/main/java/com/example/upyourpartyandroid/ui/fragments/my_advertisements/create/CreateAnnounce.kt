package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.data.entities.network.advertisement.RemoteAdsProfile

data class CreateAnnounce(
    val title: String,
    val price: String,
    val city: String,
    val category: String,
    val adsProfile: RemoteAdsProfile,
    val adsImages: List<String>
)