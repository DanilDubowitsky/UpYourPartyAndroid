package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.domain.usecase.advertisement.GetMyAdvertisementsUseCase
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IMyAdvertisementsWorkGroup : IRxWorkGroup {
    val getMyAdvertisementsUseCase: GetMyAdvertisementsUseCase
}