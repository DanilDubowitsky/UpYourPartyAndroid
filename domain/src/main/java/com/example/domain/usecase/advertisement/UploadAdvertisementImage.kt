package com.example.domain.usecase.advertisement

import com.example.domain.service.IService
import com.example.domain.usecase.global.SingleUseCase
import io.reactivex.rxjava3.core.Single
import java.io.File
import javax.inject.Inject

class UploadAdvertisementImage @Inject constructor(
    private val advertisementsService: IService.IAdvertisementsService
) : SingleUseCase<UploadAdvertisementImage.Arguments, Map<String, String>>() {

    override fun createFlow(arguments: Arguments): Single<Map<String, String>> {
        return advertisementsService.uploadAdvertisementImage(
            arguments.file,
            arguments.contentType,
            arguments.key
        )
    }

    class Arguments(
        val file: File,
        val contentType: String,
        val key: Any
    )

}