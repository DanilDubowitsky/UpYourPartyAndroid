package com.example.upyourpartyandroid.di.modules.fragments

import com.example.upyourpartyandroid.ui.fragments.advertisement.info.AboutAdvertisementFragment
import com.example.upyourpartyandroid.ui.fragments.advertisement.list.AdvertisementListFragment
import com.example.upyourpartyandroid.ui.fragments.categories.CategoriesFragment
import com.example.upyourpartyandroid.ui.fragments.favorites.FavoritesFragment
import com.example.upyourpartyandroid.ui.fragments.home.HomeFragment
import com.example.upyourpartyandroid.ui.fragments.login.LoginFragment
import com.example.upyourpartyandroid.ui.fragments.messages.MessagesFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.CreatingAdvertisementsFragment
import com.example.upyourpartyandroid.ui.fragments.profile.ProfileFragment
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationFragment
import com.example.upyourpartyandroid.ui.fragments.review.SubmitReviewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun registrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun categoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    abstract fun myAdvertisementsFragment(): MyAdvertisementsFragment

    @ContributesAndroidInjector
    abstract fun messagesFragment(): MessagesFragment

    @ContributesAndroidInjector
    abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun favoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector
    abstract fun creatingAdvertisementFragment(): CreatingAdvertisementsFragment

    @ContributesAndroidInjector
    abstract fun aboutAdvertisementFragment(): AboutAdvertisementFragment

    @ContributesAndroidInjector
    abstract fun advertisementListFragment(): AdvertisementListFragment

    @ContributesAndroidInjector
    abstract fun submitReviewFragment(): SubmitReviewFragment

}