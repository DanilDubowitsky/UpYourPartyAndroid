package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.domain.enteties.advertisement.DomainAdvertisement

data class CreatingAdvertisementsState(
    val images: ArrayList<String> = arrayListOf(),
    val announce: DomainAdvertisement? = null
)