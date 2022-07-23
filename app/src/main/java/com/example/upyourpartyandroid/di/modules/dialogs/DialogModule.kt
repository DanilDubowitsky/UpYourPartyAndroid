package com.example.upyourpartyandroid.di.modules.dialogs

import com.example.upyourpartyandroid.ui.dialogs.my_advertisements.MyAdvertisementsActionsDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DialogModule {

    @ContributesAndroidInjector
    abstract fun myAdvertisementsActions(): MyAdvertisementsActionsDialog

}
