package com.example.upyourpartyandroid.ui.fragments.advertisement

import com.example.domain.entities.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class AboutAdvertisementViewModel @Inject constructor(
    dataSource: IAboutAdvertisementWorkGroup
) : BaseMVIViewModel<AboutAdvertisementState, IAboutAdvertisementWorkGroup>(dataSource) {

    override fun createInitialState(): AboutAdvertisementState =
        AboutAdvertisementState(null)

    fun loadData(id: Long) {
        dataSource.getAdvertisement(id).handleSubscribe(onSuccess = ::handleAdvertisementData)
    }

    private fun handleAdvertisementData(advertisement: DomainFullAdvertisement) {
        reduce {
            copy(advertisement = advertisement)
        }
    }

}
