package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.android_nav.NavigationScreen
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class MyAdvertisementsViewModel @Inject constructor(
    dataSource: IMyAdvertisementsWorkGroup,
    private val router: IRouter
) : BaseMVIViewModel<MyAdvertisementsState, IMyAdvertisementsWorkGroup>(
    dataSource
) {

    override fun createInitialState(): MyAdvertisementsState = MyAdvertisementsState(advertisements = emptyList())

    fun getMyAdvertisements() {
        dataSource.getMyAdvertisements(Unit).handleSubscribe(
            onSuccess = ::handleAdvertisements
        )
    }

    fun onAdvertisementCLick(position: Int) {
        val selectedItem = currentState.advertisements[position]

        router.navigateTo(NavigationScreen.AdvertisementManager.MyAdvertisementsActions)
        router.setResultListener(NavigationScreen.AdvertisementManager.MyAdvertisementsActions.OnDeleteClick) {

        }
        router.setResultListener(NavigationScreen.AdvertisementManager.MyAdvertisementsActions.OnEditClick) {
            onEditClick(selectedItem)
        }
    }

    private fun onEditClick(item: DomainAdvertisement) {
        val screen = NavigationScreen.AdvertisementManager.AddAdvertisement(
            item.id
        )
        router.navigateTo(screen)
    }

    private fun handleAdvertisements(advertisements: List<DomainAdvertisement>) {
        reduce {
            copy(advertisements = advertisements)
        }
    }

    fun onAddAdvertisementClick() {
        val screen = NavigationScreen.AdvertisementManager.AddAdvertisement()
        router.navigateTo(screen)
    }

}