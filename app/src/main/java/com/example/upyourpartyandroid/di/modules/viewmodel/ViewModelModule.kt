package com.example.upyourpartyandroid.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upyourpartyandroid.ui.base.ViewModelFactory
import com.example.upyourpartyandroid.ui.base.ViewModelKey
import com.example.upyourpartyandroid.ui.fragments.login.ILoginWorkGroup
import com.example.upyourpartyandroid.ui.fragments.login.LoginViewModel
import com.example.upyourpartyandroid.ui.fragments.login.LoginWorkGroup
import com.example.upyourpartyandroid.ui.fragments.registration.IRegistrationWorkGroup
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationViewModel
import com.example.upyourpartyandroid.ui.fragments.registration.RegistrationWorkGroup
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @Reusable
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindLoginWorkGroup(loginWorkGroup: LoginWorkGroup): ILoginWorkGroup

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    abstract fun bindRegistrationWorkGroup(registrationWorkGroup: RegistrationWorkGroup): IRegistrationWorkGroup

}