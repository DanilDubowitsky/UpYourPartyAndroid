package com.example.domain.usecase.auth

import com.example.domain.database.IApplicationDataBase
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.usecase.global.CompletableUseCase
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class LogOut @Inject constructor(
    private val dataBase: IApplicationDataBase,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : CompletableUseCase<Unit>() {

    override fun createFlow(arguments: Unit): Completable = Completable.create { emitter ->
        dataBase.clear()
        userPreferences.clear()
        emitter.onComplete()
    }.processIOCompletable()

}