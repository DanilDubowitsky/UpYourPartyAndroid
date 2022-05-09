package com.example.data.dao

import androidx.room.*
import com.example.data.enteties.room.advertisement.FullAdvertisement
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyAdvertisementsDao {

    @Query("SELECT * FROM FULL_ADVERTISEMENT_TABLE_NAME")
    fun getAll(): Flowable<List<FullAdvertisement>>

    @Query("DELETE FROM FULL_ADVERTISEMENT_TABLE_NAME WHERE ID LIKE (:id)")
    fun delete(id: Long): Completable

    @Query("DELETE FROM FULL_ADVERTISEMENT_TABLE_NAME")
    fun deleteAll(): Completable

    @Query("SELECT * FROM FULL_ADVERTISEMENT_TABLE_NAME WHERE ID LIKE (:id)")
    fun getById(id: Long): Single<FullAdvertisement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(item: FullAdvertisement): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(items: List<FullAdvertisement>): Completable

}