package com.example.upyourpartyandroid.di.modules.database

import android.content.Context
import androidx.room.Room
import com.example.data.database.UpYourPartyDataBase
import com.example.data.database.UpYourPartyDataBase.Companion.DATA_BASE_NAME
import com.example.domain.database.IApplicationDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataBaseModule {

    @Binds
    @Singleton
    abstract fun bindDataBase(dataBase: UpYourPartyDataBase): IApplicationDataBase

    companion object {
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
}
