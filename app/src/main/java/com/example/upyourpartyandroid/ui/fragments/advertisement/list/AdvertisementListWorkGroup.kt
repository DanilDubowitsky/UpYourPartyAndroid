package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import com.example.domain.usecase.advertisement.GetAdvertisements
import javax.inject.Inject

class AdvertisementListWorkGroup @Inject constructor(
    override val getAdvertisements: GetAdvertisements

) : IAdvertisementListWorkGroup {
    override fun release() {
        getAdvertisements.release()
    }
}
