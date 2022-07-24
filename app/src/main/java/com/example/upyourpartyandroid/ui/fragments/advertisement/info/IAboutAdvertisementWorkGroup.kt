package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.advertisement.GetAdvertisement
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IAboutAdvertisementWorkGroup : IRxWorkGroup {
    val getAdvertisement: GetAdvertisement
    val changeFavoriteStatus: ChangeFavoriteStatus
}
