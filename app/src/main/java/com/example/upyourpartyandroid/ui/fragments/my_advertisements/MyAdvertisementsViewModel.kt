package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.android_nav.NavigationScreen
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class MyAdvertisementsViewModel @Inject constructor(
    dataSource: IMyAdvertisementsWorkGroup,
    private val router: IRouter
) : BaseMVIViewModel<MyAdvertisementsState, IMyAdvertisementsWorkGroup>(
    MyAdvertisementsState(advertisements = emptyList()),
    dataSource
) {

    fun getMyAdvertisements() {
        dataSource.getMyAdvertisementsUseCase(Unit).handleSubscribe(onSuccess = ::handleAdvertisements)
    }

    private fun handleAdvertisements(advertisements: List<DomainFullAdvertisement>) {
        reduce {
            state.copy(advertisements = advertisements)
        }
    }

    fun onAddAdvertisementClick() {
        val screen = NavigationScreen.AdvertisementManager.AddAdvertisement()
        router.navigateTo(screen)
    }

}