package com.example.upyourpartyandroid.ui.fragments.categories

import androidx.lifecycle.ViewModel
import com.example.android_nav.NavigationScreen
import com.example.domain.model.advertisement.DomainAdvertisementCategory
import com.example.upyourpartyandroid.navigation.IRouter
import javax.inject.Inject

class CategoriesViewModel @Inject constructor(
    private val router: IRouter
): ViewModel() {


    fun onWeddingClick() {
        navigate(DomainAdvertisementCategory.WEDDING.name)
    }

    fun onCorporateClick() {
        navigate(DomainAdvertisementCategory.CORPORATE.name)
    }

    fun onPartyClick() {
        navigate(DomainAdvertisementCategory.PARTY.name)
    }

    fun onBirthDayClick() {
        navigate(DomainAdvertisementCategory.BIRTHDAY.name)
    }

    private fun navigate(category: String) {
        val screen = NavigationScreen.AdvertisementManager.AdvertisementList(category)
        router.navigateTo(screen)
    }

}
