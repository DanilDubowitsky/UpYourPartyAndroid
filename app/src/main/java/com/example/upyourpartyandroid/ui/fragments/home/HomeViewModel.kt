package com.example.upyourpartyandroid.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.example.android_nav.NavigationScreen
import com.example.upyourpartyandroid.navigation.IRouter
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val router: IRouter
) : ViewModel() {

    fun navigateToCategories() {
        router.replace(NavigationScreen.Categories.Main, HomeViewModel::class.java.name)
    }

    fun navigateToMessages() {
        router.replace(NavigationScreen.Messages.Main, HomeViewModel::class.java.name)
    }

    fun navigateToProfile() {
        router.replace(NavigationScreen.Profile.Main, HomeViewModel::class.java.name)
    }

    fun navigateToMyAdvertisements() {
        router.replace(NavigationScreen.AdvertisementManager.Main, HomeViewModel::class.java.name)
    }

    fun navigateToFavorites() {
        router.replace(NavigationScreen.Favorites.Main, HomeViewModel::class.java.name)
    }

}