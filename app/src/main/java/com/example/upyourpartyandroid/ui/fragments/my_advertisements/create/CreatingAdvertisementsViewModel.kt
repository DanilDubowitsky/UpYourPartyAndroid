package com.example.upyourpartyandroid.ui.fragments.my_advertisements.create

import android.net.Uri
import com.example.domain.entities.advertisement.DomainAdvertisement
import com.example.domain.entities.advertisement.DomainAdvertisementCategory
import com.example.domain.usecase.advertisement.ChangeAdvertisement
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
    private val nameValidator: NameValidator,
    private val baseUrl: String
) : BaseMVIViewModel<CreatingAdvertisementsState, ICreatingAdvertisementWorkGroup>(
    dataSource
) {
    override fun createInitialState(): CreatingAdvertisementsState = CreatingAdvertisementsState()
    private var advertisementId: Long? = 0
    private val downloadedImages: HashMap<Int, String> = hashMapOf()
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
        val images = currentState.images
        val imageName = images[position].substringAfter(FILE_NAME_START_PATTERN)
        images[position] = ""
        reduce {
            copy(images = images)
        }
        dataSource.deleteAdvertisementImages(listOf(imageName)).handleMutedSubscribe {  }
    }

    fun onActionButtonClick(
        price: String,
        description: String,
        city: String,
        category: DomainAdvertisementCategory,
        title: String
    ) {
        val formattedPrice = price.trim()
        val formattedDescription = description.trim()
        val formattedCity = city.trim()
        val formattedTitle = title.trim()
        validateFields(
            formattedPrice,
            formattedDescription,
            formattedCity,
            formattedTitle
        ) {
            if (!isEditMode) {
                createAdvertisement(
                    formattedPrice,
                    formattedDescription,
                    formattedCity,
                    category,
                    title
                )
            } else {
                changeAdvertisement(
                    formattedPrice,
                    formattedDescription,
                    formattedCity,
                    category,
                    title
                )
            }
        }
    }

    private fun changeAdvertisement(
        price: String,
        description: String,
        city: String,
        category: DomainAdvertisementCategory,
        title: String
    ) {
        val imagesToRequest = currentState.images.filter(String::isNotBlank)
        val imagesNames = imagesToRequest.map { imageUrl ->
            imageUrl.substringAfter(FILE_NAME_START_PATTERN)
        }
        val downloadedImages = downloadedImages.values.toList()
        val images = imagesNames.union(downloadedImages)
        val arguments = ChangeAdvertisement.Arguments(
            advertisementId!!,
            price,
            description,city,
            category,
            title,
            images.toList()
        )
        dataSource.changeAdvertisement(arguments).handleSubscribe {
            router.back()
        }
    }

    private fun createAdvertisement(
        price: String,
        description: String,
        city: String,
        category: DomainAdvertisementCategory,
        title: String
    ) {
        val arguments = CreateAdvertisement.Arguments(
            price,
            description,
            city,
            category.name,
            title,
            downloadedImages.values.toList()
        )
        dataSource.createAdvertisement(
            arguments
        ).handleSubscribe {
            router.back()
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
        ).handleMutedSubscribe { mapResult ->
            val imageName = mapResult[FILE_NAME_KEY]
            downloadedImages[position] = imageName!!
            val currentImages = currentState.images
            currentImages[position] = "$baseUrl$FILE_NAME_START_PATTERN$imageName"
            reduce {
                copy(images = currentImages)
            }
        }
    }

    fun onBackClick() {
        router.back()
    }

    companion object {
        const val FILE_NAME_KEY = "filename"
        const val FILE_NAME_START_PATTERN = "/ads/image/"
        const val MAX_IMAGES_SIZE = 5
    }

}