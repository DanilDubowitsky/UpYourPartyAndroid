package com.example.data

import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.requests.auth.RefreshTokenRequest
import com.example.data.entities.network.requests.advertisement.CreateAdvertisementRequest
import com.example.data.entities.network.requests.advertisement.DeleteAdvertisementRequest
import com.example.data.entities.network.requests.auth.LoginRequest
import com.example.data.entities.network.requests.auth.RegistrationRequest
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface NetworkApi {

    @POST("/auth/register")
    fun registration(@Body registrationRequest: RegistrationRequest): Completable

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Single<Map<String, String>>

    @GET("/ads/my")
    fun getMyAdvertisements(@Header("Authorization") authToken: String): Single<List<RemoteAdvertisement>>

    @POST("/auth/refresher")
    fun getRefreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Single<Map<String, String>>

    @Multipart
    @POST("/ads/image/load")
    fun uploadAdvertisementImage(@Part imageFile: MultipartBody.Part): Single<Map<String, String>>

    @POST("/ads/create")
    fun createAdvertisement(
        @Body request: CreateAdvertisementRequest,
        @Header("Authorization") authToken: String
    ): Completable

    @POST("/ads/image/delete")
    fun deleteImage(@Body strList: List<String>): Completable

    @POST("/ads/remove")
    fun deleteAdvertisement(
        @Body body: DeleteAdvertisementRequest,
        @Header("Authorization")
        authToken: String
    ): Completable

}