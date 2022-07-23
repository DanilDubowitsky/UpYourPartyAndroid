package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.AdvertisementDao
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_VERSION
import com.example.data.entities.room.advertisement.AdvertisementEntity
import com.example.data.entities.room.advertisement.FullAdvertisementEntity
import com.example.domain.database.IApplicationDataBase

@Database(
    entities = [
        AdvertisementEntity::class,
        FullAdvertisementEntity::class],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UpYourPartyDataBase : RoomDatabase(), IApplicationDataBase {

    abstract fun getAdvertisementsDao(): AdvertisementDao

    override fun clear() {
        this.clearAllTables()
    }

    companion object {
        const val DATA_BASE_VERSION = 1
        const val DATA_BASE_NAME = "UpYourParty_database_name"
    }
}
