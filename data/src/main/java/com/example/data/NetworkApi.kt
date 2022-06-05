package com.example.data

import com.example.data.entities.network.advertisement.RemoteAdvertisement
import com.example.data.entities.network.requests.NetRefresh
import com.example.data.entities.network.requests.advertisement.CreateAdvertisementRequest
import com.example.data.entities.network.requests.auth.NetLogin
import com.example.data.entities.network.requests.auth.NetRegistration
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface NetworkApi {

    @POST("/auth/register")
    fun registration(@Body netRegistration: NetRegistration): Completable

    @POST("/auth/login")
    fun login(@Body netLogin: NetLogin): Single<Map<String, String>>

    @GET("/ads/my")
    fun getMyAdvertisements(@Header("Authorization") authToken: String): Single<List<RemoteAdvertisement>>

    @POST("/auth/refresher")
    fun getRefreshToken(@Body netRefresh: NetRefresh): Single<Map<String, String>>

    @Multipart
    @POST("/ads/image/load")
    fun uploadAdvertisementImage(@Part imageFile: MultipartBody.Part): Single<Map<String, String>>

    @POST("/ads/create")
    fun createAdvertisement(@Body request: CreateAdvertisementRequest, @Header("Authorization") authToken: String): Completable

    @POST("/ads/image/delete")
    fun deleteImage(@Body strList: List<String>): Completable

}