package com.example.upyourpartyandroid.di.modules.activity

import com.example.upyourpartyandroid.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}