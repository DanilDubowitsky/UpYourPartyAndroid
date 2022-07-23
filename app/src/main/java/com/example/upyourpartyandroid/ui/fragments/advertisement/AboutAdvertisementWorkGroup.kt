package com.example.upyourpartyandroid.ui.fragments.advertisement

import com.example.domain.usecase.advertisement.GetAdvertisement
import javax.inject.Inject

class AboutAdvertisementWorkGroup @Inject constructor(
    override val getAdvertisement: GetAdvertisement
) : IAboutAdvertisementWorkGroup {

    override fun release() {
        getAdvertisement.release()
    }
}
