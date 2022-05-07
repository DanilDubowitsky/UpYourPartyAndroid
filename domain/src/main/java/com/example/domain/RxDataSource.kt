package com.example.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.*

abstract class RxDataSource {

    private val disposeBag: CompositeDisposable = CompositeDisposable()

    @get:Synchronized
    private val subscriptions: MutableList<Subscription> = Collections.synchronizedList(ArrayList())

    protected fun <T: Any> Single<T>.processIOSingle(): Single<T> {
        return this.subscribeOn(Schedulers.io())
            .doOnSubscribe(::addToDisposeBag)
    }

    protected fun <T: Any> Observable<T>.processIOObservable(): Observable<T> {
        return this.subscribeOn(Schedulers.io())
            .doOnSubscribe(::addToDisposeBag)
    }

    protected fun <T: Any> Flowable<T>.processIOFlowable(): Flowable<T> {
        return this.subscribeOn(Schedulers.io())
            .doOnSubscribe { subscribtion ->
                subscriptions.add(subscribtion)
            }
    }

    protected fun Completable.processIOCompletable(): Completable {
        return this.subscribeOn(Schedulers.io())
            .doOnSubscribe(::addToDisposeBag)
    }

    private fun addToDisposeBag(disposable: Disposable) {
        disposeBag.add(disposable)
    }

    fun release() {
        disposeBag.clear()
        subscriptions.forEach { subscription ->
            subscription.cancel()
        }
    }

}