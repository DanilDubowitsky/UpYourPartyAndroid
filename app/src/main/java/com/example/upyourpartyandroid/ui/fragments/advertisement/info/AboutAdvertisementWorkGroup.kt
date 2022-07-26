package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.advertisement.GetAdvertisement
import com.example.domain.usecase.review.GetReviews
import javax.inject.Inject

class AboutAdvertisementWorkGroup @Inject constructor(
    override val getAdvertisement: GetAdvertisement,
    override val changeFavoriteStatus: ChangeFavoriteStatus,
    override val getReviews: GetReviews
) : IAboutAdvertisementWorkGroup {

    override fun release() {
        getAdvertisement.release()
        changeFavoriteStatus.release()
        getReviews.release()
    }
}
