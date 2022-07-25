package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.advertisement.GetAdvertisements
import javax.inject.Inject

class AdvertisementListWorkGroup @Inject constructor(
    override val getAdvertisements: GetAdvertisements,
    override val addToFavorite: ChangeFavoriteStatus

) : IAdvertisementListWorkGroup {
    override fun release() {
        addToFavorite.release()
        getAdvertisements.release()
    }
}
