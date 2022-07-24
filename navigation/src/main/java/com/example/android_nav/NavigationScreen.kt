package com.example.android_nav

interface NavigationScreen : Screen {

    sealed interface Categories : NavigationScreen {

        object Main : Categories

    }

    sealed interface AdvertisementManager : NavigationScreen {

        object Main : AdvertisementManager

        data class AddAdvertisement(
            val id: Long? = null
        ) : AdvertisementManager

        object MyAdvertisementsActions : AdvertisementManager {

            object OnDeleteClick : ResultKey<Unit>

            object OnEditClick : ResultKey<Unit>
        }

        data class AdvertisementInfo(
            val id: Long
        ) : AdvertisementManager

        data class AdvertisementList(
            val category: String
        ) : AdvertisementManager

    }

    sealed interface Favorites : NavigationScreen {

        object Main : Favorites

    }

    sealed interface Messages : NavigationScreen {

        object Main : Messages

    }

    sealed interface Profile : NavigationScreen {

        object Main : Profile

    }

    sealed interface Main : NavigationScreen {

        object Home : Main

    }

    sealed interface Auth : NavigationScreen {

        object Login : Auth

        object Registration : Auth

    }

}