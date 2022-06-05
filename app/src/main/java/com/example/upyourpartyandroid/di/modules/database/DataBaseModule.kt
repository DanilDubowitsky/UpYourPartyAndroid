package com.example.upyourpartyandroid.di.modules.database

import android.content.Context
import androidx.room.Room
import com.example.data.database.UpYourPartyDataBase
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideUpYourPartyDataBase(context: Context): UpYourPartyDataBase {
        return Room.databaseBuilder(context, UpYourPartyDataBase::class.java, DATA_BASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideAdvertisementDao(dataBase: UpYourPartyDataBase) = dataBase.getAdvertisementsDao()

}
