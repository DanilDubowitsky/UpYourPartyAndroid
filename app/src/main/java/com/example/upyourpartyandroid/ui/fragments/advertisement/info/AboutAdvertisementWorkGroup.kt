package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.advertisement.GetAdvertisement
import javax.inject.Inject

class AboutAdvertisementWorkGroup @Inject constructor(
    override val getAdvertisement: GetAdvertisement,
    override val changeFavoriteStatus: ChangeFavoriteStatus
) : IAboutAdvertisementWorkGroup {

    override fun release() {
        getAdvertisement.release()
        changeFavoriteStatus.release()
    }
}
