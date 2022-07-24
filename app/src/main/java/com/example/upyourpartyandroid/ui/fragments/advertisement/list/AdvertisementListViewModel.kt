package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import com.example.data.converters.toEnumModel
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.entities.advertisement.DomainFullAdvertisement
import com.example.domain.usecase.advertisement.GetAdvertisements
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class AdvertisementListViewModel @Inject constructor(
    dataSource: IAdvertisementListWorkGroup
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

}
