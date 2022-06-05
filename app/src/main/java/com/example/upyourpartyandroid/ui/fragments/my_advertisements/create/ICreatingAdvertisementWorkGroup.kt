package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import com.example.domain.usecase.advertisement.CreateAdvertisement
import com.example.domain.usecase.advertisement.IProgressListener
import com.example.domain.usecase.advertisement.UploadAdvertisementImageUseCase
import com.example.upyourpartyandroid.ui.base.IRxWorkGroup
import com.example.upyourpartyandroid.ui.helpers.FilesHelper

interface ICreatingAdvertisementWorkGroup : IRxWorkGroup {
    val uploadImageAdvertisementUseCase: UploadAdvertisementImageUseCase
    val uploadProgressListener: IProgressListener
    val filesHelper: FilesHelper
    val createAdvertisement: CreateAdvertisement
}