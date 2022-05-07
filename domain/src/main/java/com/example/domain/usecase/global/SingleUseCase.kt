package com.example.domain.usecase.global

import com.example.domain.RxDataSource
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<T, R: Any> : RxDataSource() {

    protected abstract fun createFlow(arguments: T): Single<R>

    operator fun invoke(arguments: T): Single<R> {
        val flow = createFlow(arguments)
        return flow.processIOSingle()
    }

}