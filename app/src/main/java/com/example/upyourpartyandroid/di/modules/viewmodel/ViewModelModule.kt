package com.example.upyourpartyandroid.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upyourpartyandroid.ui.activities.MainActivityViewModel
import com.example.upyourpartyandroid.ui.base.ViewModelFactory
import com.example.upyourpartyandroid.ui.base.ViewModelKey
import com.example.upyourpartyandroid.ui.fragments.home.HomeViewModel
import com.example.upyourpartyandroid.ui.fragments.login.ILoginWorkGroup
import com.example.upyourpartyandroid.ui.fragments.login.LoginViewModel
import com.example.upyourpartyandroid.ui.fragments.login.LoginWorkGroup
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.IMyAdvertisementsWorkGroup
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsViewModel
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.MyAdvertisementsWorkGroup
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.CreatingAdvertisementWorkGroup
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.CreatingAdvertisementsViewModel
import com.example.upyourpartyandroid.ui.fragments.my_advertisements.create.ICreatingAdvertisementWorkGroup
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

    @Binds
    @IntoMap
    @ViewModelKey(MyAdvertisementsViewModel::class)
    abstract fun bindViewModel(viewModel: MyAdvertisementsViewModel): ViewModel

    @Binds
    abstract fun bindMyAdvertisementsWorkGroup(workGroup: MyAdvertisementsWorkGroup): IMyAdvertisementsWorkGroup

    @Binds
    @IntoMap
    @ViewModelKey(CreatingAdvertisementsViewModel::class)
    abstract fun bindCreatingAdvertisementViewModel(viewModel: CreatingAdvertisementsViewModel): ViewModel

    @Binds
    abstract fun bindCreatingAdvertisementWorkGroup(workGroup: CreatingAdvertisementWorkGroup): ICreatingAdvertisementWorkGroup

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

}