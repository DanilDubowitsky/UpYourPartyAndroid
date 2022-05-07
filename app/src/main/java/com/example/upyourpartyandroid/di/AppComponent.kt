package com.example.upyourpartyandroid.di

import com.example.upyourpartyandroid.app.App
import com.example.upyourpartyandroid.di.modules.activity.ActivityModule
import com.example.upyourpartyandroid.di.modules.database.DataBaseModule
import com.example.upyourpartyandroid.di.modules.fragments.FragmentsModule
import com.example.upyourpartyandroid.di.modules.network.RetrofitModule
import com.example.upyourpartyandroid.di.modules.preferences.PreferencesModule
import com.example.upyourpartyandroid.di.modules.repository.RepositoryModule
import com.example.upyourpartyandroid.di.modules.service.ServiceModule
import com.example.upyourpartyandroid.di.modules.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    DataBaseModule::class,
    RepositoryModule::class,
    RetrofitModule::class,
    FragmentsModule::class,
    ActivityModule::class,
    ViewModelModule::class,
    ServiceModule::class,
    PreferencesModule::class
])
@Singleton
interface AppComponent : AndroidInjector<App> {

    override fun inject(instance: App)

    @Component.Builder
    interface AppBuilder {

        @BindsInstance
        fun bindInstance(app: App): AppBuilder

        fun build(): AppComponent

    }

}