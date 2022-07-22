package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import android.net.Uri
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.usecase.advertisement.CreateAdvertisement
import com.example.domain.usecase.advertisement.UploadAdvertisementImage
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.Utils.formatPhoneToString
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import com.example.upyourpartyandroid.ui.fragments.validation.NameValidator
import javax.inject.Inject

class CreatingAdvertisementsViewModel @Inject constructor(
    dataSource: ICreatingAdvertisementWorkGroup,
    private val router: IRouter,
    private val nameValidator: NameValidator
) : BaseMVIViewModel<CreatingAdvertisementsState, ICreatingAdvertisementWorkGroup>(
    dataSource
) {
    override fun createInitialState(): CreatingAdvertisementsState = CreatingAdvertisementsState()
    private val downloadedImages: HashMap<Int, String> = hashMapOf()
    private var advertisementId: Long? = 0
    private val isEditMode get() = advertisementId != null

    fun prepare(advertisementId: Long?) {
        this.advertisementId = advertisementId
        if (!isEditMode) initEmptyList()
        else loadAdvertisementData()
    }

    private fun loadAdvertisementData() {
        dataSource.getAdvertisementById(advertisementId!!)
            .handleSubscribe(onSuccess = ::handleAdvertisement)
    }

    private fun handleAdvertisement(advertisement: DomainAdvertisement) = reduce {
        val imagesList = arrayListOf<String>()
        imagesList.addAll(advertisement.images)
        for (i in imagesList.size until MAX_IMAGES_SIZE) {
            imagesList.add("")
        }
        copy(
            images = imagesList,
            announce = advertisement
        )
    }


    private fun initEmptyList() =
        reduce {
            copy(
                images = arrayListOf(
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            )
        }

    private fun validateFields(
        price: String,
        description: String,
        phone: String,
        city: String,
        title: String,
        onSuccess: () -> Unit
    ) {
        var isValid = true
        if (price.isBlank()) {
            isValid = false
            postSideEffect(CreatingAdvertisementsSideEffects.PriceInvalid)
        }
        if (description.isBlank()) {
            isValid = false
            postSideEffect(CreatingAdvertisementsSideEffects.DescriptionInvalid)
        }
        if (!nameValidator.validate(city)) {
            isValid = false
            postSideEffect(CreatingAdvertisementsSideEffects.CityInvalid)
        }
        if (!nameValidator.validate(title)) {
            isValid = false
            postSideEffect(CreatingAdvertisementsSideEffects.NameInvalid)
        }
        if (phone.isBlank()) {
            isValid = false
            postSideEffect(CreatingAdvertisementsSideEffects.PhoneInvalid)
        }
        if (isValid) onSuccess.invoke()
    }

    fun handleImagePick(uri: Uri?, position: Int) {
        if (uri != null) {
            val newState = currentState.images
            newState[position] = uri.toString()
            reduce {
                copy(images = newState)
            }
            uploadImage(uri, position)
        }
    }

    fun deleteImage(position: Int) {
        //dataSource.deleteAdvertisementImages()
    }

    fun createAdvertisement(
        price: String,
        description: String,
        phone: String,
        city: String,
        category: DomainAdvertisementCategory,
        title: String
    ) {
        val formattedPrice = price.trim()
        val formattedDescription = description.trim()
        val formattedPhone = phone.trim().formatPhoneToString()
        val formattedCity = city.trim()
        val formattedTitle = title.trim()
        validateFields(
            formattedPrice,
            formattedDescription,
            formattedPhone,
            formattedCity,
            formattedTitle
        ) {
            val arguments = CreateAdvertisement.Arguments(
                formattedPrice,
                formattedDescription,
                formattedCity,
                category.name,
                formattedTitle,
                downloadedImages.values.toList()
            )
            dataSource.createAdvertisement(
                arguments
            ).handleSubscribe {
                router.back()
            }
        }
    }

    private fun uploadImage(uri: Uri, position: Int) {
        val file = dataSource.filesHelper.createImageFile(uri)
        val mimeType = dataSource.filesHelper.getMimeType(uri)
        dataSource.uploadImageAdvertisement(
            UploadAdvertisementImage.Arguments(
                file,
                mimeType,
                position
            )
        ).handleSubscribe { mapResult ->
            val imageName = mapResult[FILE_NAME_KEY]
            downloadedImages[position] = imageName!!
        }
    }

    fun onBackClick() {
        router.back()
    }

    companion object {
        const val FILE_NAME_KEY = "filename"
        const val MAX_IMAGES_SIZE = 5
    }

}