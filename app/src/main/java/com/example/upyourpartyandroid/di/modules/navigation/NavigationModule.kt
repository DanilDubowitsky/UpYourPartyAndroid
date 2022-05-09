package com.example.upyourpartyandroid.di.modules.navigation

import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.navigation.Router
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindRouter(router: Router): IRouter

}