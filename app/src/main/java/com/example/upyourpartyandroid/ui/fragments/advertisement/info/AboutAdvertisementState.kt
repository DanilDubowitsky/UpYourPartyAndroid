package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.model.review.Review

data class AboutAdvertisementState(
    val advertisement: DomainFullAdvertisement?,
    val reviews: List<Review>
)
