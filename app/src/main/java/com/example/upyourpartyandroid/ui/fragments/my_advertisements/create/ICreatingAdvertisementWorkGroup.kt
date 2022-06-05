package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.domain.usecase.advertisement.*
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup
import com.example.upyourpartyandroid.ui.helpers.FilesHelper

interface ICreatingAdvertisementWorkGroup : IRxWorkGroup {
    val uploadImageAdvertisement: UploadAdvertisementImage
    val uploadProgressListener: IProgressListener
    val filesHelper: FilesHelper
    val createAdvertisement: CreateAdvertisement
    val deleteAdvertisementImages: DeleteAdvertisementImages
    val getAdvertisementById: GetAdvertisementById
}