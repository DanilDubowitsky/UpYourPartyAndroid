package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class MyAdvertisementsViewModel @Inject constructor(
    dataSource: IMyAdvertisementsWorkGroup
) : BaseMVIViewModel<MyAdvertisementsState, IMyAdvertisementsWorkGroup>(
    MyAdvertisementsState(emptyList()),
    dataSource
) {

    fun getMyAdvertisements() {
        dataSource.getMyAdvertisementsUseCase(Unit).handleSubscribe(onSuccess = ::handleAdvertisements)
    }

    private fun handleAdvertisements(advertisements: List<DomainFullAdvertisement>) {
        reduce {
            state.copy(advertisements = advertisements)
        }
    }

    fun onAddAdvertisementClick() {
        postSideEffect(MyAdvertisementsSideEffects.NavigateToCreatingAdvertisement)
    }

}