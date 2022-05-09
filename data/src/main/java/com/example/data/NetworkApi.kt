package com.example.data

import com.example.data.enteties.network.NetLogin
import com.example.data.enteties.network.NetRegistration
import com.example.data.enteties.network.advertisement.NetFullAdvertisement
import com.example.data.enteties.room.advertisement.FullAdvertisement
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkApi {

    @POST("/auth/register")
    fun registration(@Body netRegistration: NetRegistration): Completable

    @POST("/auth/login")
    fun login(@Body netLogin: NetLogin): Single<Map<String, String>>

    @GET("ads/my")
    fun getMyAdvertisements(@Header("Authorization") authToken: String): Single<List<NetFullAdvertisement>>

}