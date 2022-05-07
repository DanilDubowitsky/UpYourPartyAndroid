package com.example.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.database.UpYourPartyDataBase.Companion.ADVERTISEMENT_TABLE_NAME
import com.example.data.enteties.room.Advertisement
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface AdvertisementDao {

    @Query("SELECT * FROM $ADVERTISEMENT_TABLE_NAME WHERE CATEGORY LIKE (:category)")
    fun getAllAdvertisement(category: String): Flowable<List<Advertisement>>

    @Query("DELETE FROM $ADVERTISEMENT_TABLE_NAME WHERE CATEGORY LIKE (:category)")
    fun deleteAllAdvertisement(category: String): Completable

}