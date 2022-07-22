package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.domain.usecase.advertisement.DeleteAdvertisement
import com.example.domain.usecase.advertisement.GetMyAdvertisements
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IMyAdvertisementsWorkGroup : IRxWorkGroup {
    val getMyAdvertisements: GetMyAdvertisements
    val deleteAdvertisement: DeleteAdvertisement
}