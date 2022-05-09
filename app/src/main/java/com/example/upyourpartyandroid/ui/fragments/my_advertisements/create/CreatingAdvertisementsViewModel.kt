package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class CreatingAdvertisementsViewModel @Inject constructor(
    dataSource: ICreatingAdvertisementWorkGroup
) : BaseMVIViewModel<CreatingAdvertisementsState, ICreatingAdvertisementWorkGroup>(
    CreatingAdvertisementsState(),
    dataSource
) {
}