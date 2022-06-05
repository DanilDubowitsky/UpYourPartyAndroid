package com.example.upyourpartyandroid.navigation

import com.example.android_nav.FragmentScreen
import com.example.android_nav.NavigationScreen
import com.example.android_nav.Screen
import com.example.upyourpartyandroid.ui.fragments.categories.CategoriesFragment
import com.example.upyourpartyandroid.ui.fragments.favorites.FavoritesFragment
import com.example.upyourpartyandroid.ui.fragments.home.HomeFragment
import com.example.upyourpartyandroid.ui.fragments.login.LoginFragment
import com.example.upyourpartyandroid.ui.fragments.messages.MessagesFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.CreatingAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.profile.ProfileFragment
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenCreator @Inject constructor() {

    fun createScreen(screen: NavigationScreen): Screen =
        when (screen) {
            is NavigationScreen.Messages -> createPlatformScreen(screen)
            is NavigationScreen.Favorites -> createPlatformScreen(screen)
            is NavigationScreen.AdvertisementManager -> createPlatformScreen(screen)
            is NavigationScreen.Profile -> createPlatformScreen(screen)
            is NavigationScreen.Categories -> createPlatformScreen(screen)
            is NavigationScreen.Main -> createPlatformScreen(screen)
            is NavigationScreen.Auth -> createPlatformScreen(screen)
            else -> throw IllegalStateException("Unknown screen type")
        }

    private fun createPlatformScreen(screen: NavigationScreen.Auth) =
        when(screen) {
            is NavigationScreen.Auth.Login -> FragmentScreen {
                LoginFragment()
            }
            is NavigationScreen.Auth.Registration -> FragmentScreen {
                RegistrationFragment()
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.Main) =
        when(screen) {
            is NavigationScreen.Main.Home -> FragmentScreen {
                HomeFragment()
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.Messages) =
        when (screen) {
            is NavigationScreen.Messages.Main -> FragmentScreen {
                MessagesFragment()
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.Favorites) =
        when (screen) {
            is NavigationScreen.Favorites.Main -> FragmentScreen {
                FavoritesFragment()
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.AdvertisementManager) =
        when (screen) {

            is NavigationScreen.AdvertisementManager.Main -> FragmentScreen {
                MyAdvertisementsFragment()
            }

            is NavigationScreen.AdvertisementManager.AddAdvertisement -> FragmentScreen {
                CreatingAdvertisementsFragment().apply {
                    advertisementId = screen.id
                }
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.Profile) =
        when (screen) {
            is NavigationScreen.Profile.Main -> FragmentScreen {
                ProfileFragment()
            }
        }

    private fun createPlatformScreen(screen: NavigationScreen.Categories) =
        when (screen) {
            is NavigationScreen.Categories.Main -> FragmentScreen {
                CategoriesFragment()
            }
        }

}