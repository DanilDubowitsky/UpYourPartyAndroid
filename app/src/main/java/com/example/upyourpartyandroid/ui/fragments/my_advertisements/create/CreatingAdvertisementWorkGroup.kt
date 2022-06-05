package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.domain.usecase.advertisement.CreateAdvertisement
import com.example.domain.usecase.advertisement.IProgressListener
import com.example.domain.usecase.advertisement.UploadAdvertisementImageUseCase
import com.example.upyourpartyandroid.ui.helpers.FilesHelper
import javax.inject.Inject

class CreatingAdvertisementWorkGroup @Inject constructor(
    override val uploadImageAdvertisementUseCase: UploadAdvertisementImageUseCase,
    override val filesHelper: FilesHelper,
    override val uploadProgressListener: IProgressListener,
    override val createAdvertisement: CreateAdvertisement
) : ICreatingAdvertisementWorkGroup {

    override fun release() {
        uploadImageAdvertisementUseCase.release()
        uploadProgressListener.release()
        createAdvertisement.release()
    }

}
