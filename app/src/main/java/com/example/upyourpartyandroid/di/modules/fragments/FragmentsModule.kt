package com.example.upyourpartyandroid.di.modules.fragments

import com.example.upyourpartyandroid.ui.fragments.login.LoginFragment
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun registrationFragment(): RegistrationFragment

}