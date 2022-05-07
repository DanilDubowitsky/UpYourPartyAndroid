package com.example.domain.usecase.global

import com.example.domain.RxDataSource
import io.reactivex.rxjava3.core.Flowable

abstract class FlowableUseCase<T, R: Any> : RxDataSource() {

    abstract fun createFlow(arguments: T): Flowable<R>

    operator fun invoke(arguments: T): Flowable<R> {
        val flow = createFlow(arguments)
        return flow.processIOFlowable()
    }

}