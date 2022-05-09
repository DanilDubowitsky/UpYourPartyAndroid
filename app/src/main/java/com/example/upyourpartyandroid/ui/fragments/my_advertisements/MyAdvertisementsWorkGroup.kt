package com.example.upyourpartyandroid.ui.fragments.my_advertisements

import com.example.domain.repository.IRxRepositoryContract
import com.example.domain.usecase.advertisement.GetMyAdvertisementsUseCase
import javax.inject.Inject

class MyAdvertisementsWorkGroup @Inject constructor(
    override val getMyAdvertisementsUseCase: GetMyAdvertisementsUseCase,
    override val myAdvertisementsRepository: IRxRepositoryContract.IMyAdvertisementsRepository
) : IMyAdvertisementsWorkGroup {

    override fun release() {
        getMyAdvertisementsUseCase.release()
        myAdvertisementsRepository.release()
    }

}