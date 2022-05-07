package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.AdvertisementDao
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_VERSION
import com.example.data.enteties.room.Advertisement

@Database(
    entities = [Advertisement::class],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UpYourPartyDataBase : RoomDatabase() {

    abstract fun getAdvertisementsDao(): AdvertisementDao

    companion object {
        const val ADVERTISEMENT_TABLE_NAME = "advertisement_table_name"
        const val DATA_BASE_VERSION = 1
        const val DATA_BASE_NAME = "UpYourParty_database_name"
    }

}