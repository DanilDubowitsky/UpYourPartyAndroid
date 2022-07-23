package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationSideEffect

sealed class CreatingAdvertisementsSideEffects : BaseSideEffects {

    object NameInvalid : CreatingAdvertisementsSideEffects()

    object CityInvalid : CreatingAdvertisementsSideEffects()

    object PhoneInvalid : CreatingAdvertisementsSideEffects()

    object DescriptionInvalid : CreatingAdvertisementsSideEffects()

    object PriceInvalid : CreatingAdvertisementsSideEffects()

}