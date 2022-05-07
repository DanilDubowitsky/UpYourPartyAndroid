package com.example.upyourpartyandroid.di.modules.preferences

import android.content.Context
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.preferences.UserPreferences
import com.example.domain.preferences.UserPreferences.Companion.USER_PREFS_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun provideUserPreferences(context: Context): IPreferencesContract.IUserPreferences {
        val prefs = context.getSharedPreferences(USER_PREFS_NAME, Context.MODE_PRIVATE)
        return UserPreferences(prefs)
    }

}