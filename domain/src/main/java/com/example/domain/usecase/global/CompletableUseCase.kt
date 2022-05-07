package com.example.domain.usecase.global

import com.example.domain.RxDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

abstract class CompletableUseCase<T> : RxDataSource() {

    protected abstract fun createFlow(arguments: T): Completable

    operator fun invoke(arguments: T): Completable {
        val flow = createFlow(arguments)
        return flow.processIOCompletable()
    }

}
