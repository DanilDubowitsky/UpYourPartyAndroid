package com.example.domain.usecase.global

import com.example.domain.RxDataSource
import io.reactivex.rxjava3.core.Observable

abstract class ObservableUseCase<T, R: Any> : RxDataSource() {

    protected abstract fun createFlow(arguments: T): Observable<R>

    operator fun invoke(arguments: T): Observable<R> {
        val flow = createFlow(arguments)
        return flow.processIOObservable()
    }

}