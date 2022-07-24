package com.example.upyourpartyandroid.ui.fragments.advertisement.list

import com.example.domain.usecase.advertisement.GetAdvertisements
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup

interface IAdvertisementListWorkGroup : IRxWorkGroup {
    val getAdvertisements: GetAdvertisements
}
