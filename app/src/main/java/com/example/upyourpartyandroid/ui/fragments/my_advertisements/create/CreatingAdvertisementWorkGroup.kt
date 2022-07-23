package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.domain.usecase.advertisement.*
import com.example.upyourpartyandroid.ui.helpers.FilesHelper
import javax.inject.Inject

class CreatingAdvertisementWorkGroup @Inject constructor(
    override val uploadImageAdvertisement: UploadAdvertisementImage,
    override val filesHelper: FilesHelper,
    override val uploadProgressListener: IProgressListener,
    override val createAdvertisement: CreateAdvertisement,
    override val deleteAdvertisementImages: DeleteAdvertisementImages,
    override val getMyAdvertisement: GetMyAdvertisement,
    override val changeAdvertisement: ChangeAdvertisement
) : ICreatingAdvertisementWorkGroup {

    override fun release() {
        uploadImageAdvertisement.release()
        uploadProgressListener.release()
        createAdvertisement.release()
        deleteAdvertisementImages.release()
        getMyAdvertisement.release()
        changeAdvertisement.release()
    }

}
