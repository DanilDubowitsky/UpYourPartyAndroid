package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.AdvertisementDao
import com.example.data.dao.MyAdvertisementsDao
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_VERSION
import com.example.data.enteties.room.advertisement.Advertisement
import com.example.data.enteties.room.advertisement.FullAdvertisement

@Database(
    entities = [Advertisement::class, FullAdvertisement::class],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UpYourPartyDataBase : RoomDatabase() {

    abstract fun getAdvertisementsDao(): AdvertisementDao

    abstract fun getMyAdvertisementsDao(): MyAdvertisementsDao

    companion object {
        const val DATA_BASE_VERSION = 1
        const val DATA_BASE_NAME = "UpYourParty_database_name"
    }

}