package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
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

    fun changeFavoriteStatus() {
        currentState.advertisement?.let { advertisement ->
            val arguments = ChangeFavoriteStatus.Arguments(
                advertisement.id,
                !advertisement.isFavorite
            )
            dataSource.changeFavoriteStatus(arguments).handleMutedSubscribe {}
        }
    }

}
