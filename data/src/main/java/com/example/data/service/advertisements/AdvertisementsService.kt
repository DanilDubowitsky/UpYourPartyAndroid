package com.example.data.service.advertisements

import com.example.data.NetworkApi
import com.example.data.converters.remote.toDomain
import com.example.data.entities.network.advertisement.RemoteAdsProfile
import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.requests.advertisement.CreateAdvertisementRequest
import com.example.domain.enteties.advertisement.DomainAdvertisement
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import com.example.domain.usecase.advertisement.IProgressListener
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class AdvertisementsService @Inject constructor(
    private val api: NetworkApi,
    private val userPreferences: IPreferencesContract.IUserPreferences,
    private val progressListener: IProgressListener,
    private val apiUrl: String
) : IService.IAdvertisementsService {

    override fun getMyAdvertisements(): Single<List<DomainAdvertisement>> {
        val currentUserId = userPreferences.userId
        val token = userPreferences.authToken ?: throw Exception("user is not authorized")
        return api.getMyAdvertisements(token).map { remoteAdvertisements ->
            remoteAdvertisements.map { remoteAdvertisement ->
                remoteAdvertisement.toDomain(currentUserId, "$apiUrl/ads/image/")
            }
        }
    }

    override fun uploadAdvertisementImage(
        file: File,
        mimeType: String,
        key: Any
    ): Single<Map<String, String>> {
        val progressRequestBody = ProgressRequestBody(
            file,
            mimeType,
            progressListener,
            key
        )
        val request = MultipartBody.Part.createFormData("file", file.name, progressRequestBody)
        return api.uploadAdvertisementImage(request)
            .doOnError {
                progressListener.removeProgressListener(key)
            }
    }

    override fun createAdvertisement(
        price: String,
        description: String,
        city: String,
        category: String,
        title: String,
        images: List<String>
    ): Completable {
        val request = CreateAdvertisementRequest(
            price,
            RemoteAdsProfile(description),
            city,
            category,
            title,
            images
        )
        val token = userPreferences.authToken ?: throw Exception("user is not authorized")

        return api.createAdvertisement(request, token)
    }

    override fun deleteAdvertisementsImages(images: List<String>): Completable {
        return api.deleteImage(images)
    }

}
