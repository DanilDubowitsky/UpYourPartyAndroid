package com.example.data.dao

import androidx.room.*
import com.example.data.entities.room.advertisement.AdvertisementEntity
import com.example.data.entities.room.advertisement.FullAdvertisementEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface AdvertisementDao {

    @Query("SELECT * FROM AdvertisementEntity WHERE category == :category")
    fun getAllAdvertisement(category: String): Flowable<List<AdvertisementEntity>>

    @Query("DELETE FROM AdvertisementEntity WHERE category == :category")
    fun deleteAllAdvertisements(category: String): Completable

    @Query("SELECT * FROM FullAdvertisementEntity WHERE id == :id")
    fun getFullAdvertisement(id: Long): Single<FullAdvertisementEntity>

    @Query("DELETE FROM FullAdvertisementEntity WHERE id == :id")
    fun deleteFullAdvertisement(id: Long): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(advertisement: AdvertisementEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(advertisement: FullAdvertisementEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateAdvertisements(advertisements: List<AdvertisementEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFullAdvertisements(advertisements: List<FullAdvertisementEntity>): Completable

    @Transaction
    fun deleteAll(category: String) {
        deleteAllFullAdvertisements(category).blockingAwait()
        deleteAllAdvertisements(category).blockingAwait()
    }

    @Query("DELETE FROM FullAdvertisementEntity WHERE category == :category")
    fun deleteAllFullAdvertisements(category: String): Completable

    @Query("SELECT * FROM AdvertisementEntity WHERE isMy == :isMy")
    fun getMyAdvertisements(isMy: Boolean = true): Flowable<List<AdvertisementEntity>>

    @Query("SELECT * FROM AdvertisementEntity WHERE id == :id")
    fun getAdvertisement(id: Long): Single<AdvertisementEntity>

}