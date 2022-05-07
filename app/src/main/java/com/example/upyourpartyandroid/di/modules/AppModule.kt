package com.example.upyourpartyandroid.di.modules

import android.content.Context
import com.example.upyourpartyandroid.app.App
import com.example.upyourpartyandroid.di.modules.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindContext(app: App): Context

}