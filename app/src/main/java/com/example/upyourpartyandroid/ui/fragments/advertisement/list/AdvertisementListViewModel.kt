package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import com.example.android_nav.NavigationScreen
import com.example.data.converters.toEnumModel
import com.example.domain.model.advertisement.DomainAdvertisement
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.advertisement.GetAdvertisements
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class AdvertisementListViewModel @Inject constructor(
    dataSource: IAdvertisementListWorkGroup,
    private val router: IRouter
) : BaseMVIViewModel<AdvertisementListState, IAdvertisementListWorkGroup>(dataSource) {

    override fun createInitialState(): AdvertisementListState = AdvertisementListState(emptyList())

    fun loadData(category: String)  {
        val enumCategory = category.toEnumModel<DomainAdvertisementCategory>()
        val arguments = GetAdvertisements.Arguments(
            enumCategory,
            "",
            "id",
            ""
        )
        dataSource.getAdvertisements(arguments).handleSubscribe(onSuccess = ::handleAdvertisements)
    }

    private fun handleAdvertisements(advertisements: List<DomainAdvertisement>) = reduce {
        copy(advertisements = advertisements)
    }

    fun onHolderClick(position: Int) {
        val item = currentState.advertisements[position]
        val screen = NavigationScreen.AdvertisementManager.AdvertisementInfo(item.id)
        router.navigateTo(screen)
    }

    fun onFavoriteClick(position: Int) {
        val item = currentState.advertisements[position]
        val arguments = ChangeFavoriteStatus.Arguments(item.id, !item.isFavorite)
        dataSource.addToFavorite(arguments).handleMutedSubscribe {}
    }

    fun onBackClick() {
        router.back()
    }

}
