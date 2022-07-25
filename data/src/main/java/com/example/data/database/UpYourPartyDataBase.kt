package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.dao.AdvertisementDao
import com.example.data.dao.ReviewDao
import com.example.data.dao.UserDao
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_VERSION
import com.example.data.entities.room.advertisement.AdvertisementEntity
import com.example.data.entities.room.advertisement.FullAdvertisementEntity
import com.example.data.entities.room.review.ReviewEntity
import com.example.data.entities.room.user.UserEntity
import com.example.domain.database.IApplicationDataBase

@Database(
    entities = [
        AdvertisementEntity::class,
        FullAdvertisementEntity::class,
        ReviewEntity::class,
        UserEntity::class],
    version = DATA_BASE_VERSION,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class UpYourPartyDataBase : RoomDatabase(), IApplicationDataBase {

    abstract val advertisementDao: AdvertisementDao
    abstract val reviewDao: ReviewDao
    abstract val userDao: UserDao

    override fun clear() {
        this.clearAllTables()
    }

    companion object {
        const val DATA_BASE_VERSION = 1
        const val DATA_BASE_NAME = "UpYourParty_database_name"
    }
}
