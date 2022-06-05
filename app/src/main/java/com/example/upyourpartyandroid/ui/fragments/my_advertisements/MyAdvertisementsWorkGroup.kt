package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.domain.usecase.advertisement.GetMyAdvertisements
import javax.inject.Inject

class MyAdvertisementsWorkGroup @Inject constructor(
    override val getMyAdvertisements: GetMyAdvertisements,
) : IMyAdvertisementsWorkGroup {

    override fun release() {
        getMyAdvertisements.release()
    }

}