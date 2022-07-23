package com.example.upyourpartyandroid.ui.fragments.advertisement

import com.example.domain.usecase.advertisement.GetAdvertisement
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IAboutAdvertisementWorkGroup : IRxWorkGroup {
    val getAdvertisement: GetAdvertisement
}
