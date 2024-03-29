package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.android_nav.NavigationScreen
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import java.util.concurrent.TimeUnit
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
            onDeleteClick(selectedItem)
        }
        router.setResultListener(NavigationScreen.AdvertisementManager.MyAdvertisementsActions.OnEditClick) {
            onEditClick(selectedItem)
        }
    }

    private fun onDeleteClick(item: DomainAdvertisement) {
        dataSource.deleteAdvertisement(item.id).handleMutedSubscribe {  }
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

    fun onItemClick(position: Int) {
        val item = currentState.advertisements[position]
        val screen = NavigationScreen.AdvertisementManager.AdvertisementInfo(item.id)
        router.navigateTo(screen)
    }

    fun onAddAdvertisementClick() {
        val screen = NavigationScreen.AdvertisementManager.AddAdvertisement()
        router.navigateTo(screen)
    }

    override fun onCleared() {

        super.onCleared()
    }

}