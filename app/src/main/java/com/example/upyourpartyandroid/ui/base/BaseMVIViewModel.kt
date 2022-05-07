package com.example.upyourpartyandroid.ui.base

import androidx.lifecycle.*
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import com.example.upyourpartyandroid.ui.fragments.registration.IRegistrationWorkGroup
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

abstract class BaseMVIViewModel<STATE, WG: IRxWorkGroup>(
    initialState: STATE,
    protected val dataSource: WG
) : ViewModel() {

    protected var state: STATE = initialState

    private val stateSubject: BehaviorSubject<STATE> = BehaviorSubject.createDefault(initialState)

    private val sideEffectSubject: PublishSubject<BaseSideEffects> = PublishSubject.create()

    fun observe(
        lifecycleOwner: LifecycleOwner,
        onStateChange: (STATE) -> Unit,
        onSideEffect: (BaseSideEffects) -> Unit
    ) {
        val lifeCycleObserver =
            MviLifeCycleObserver(
                stateSubject,
                sideEffectSubject,
                onStateChange,
                onSideEffect
            )
        lifecycleOwner.lifecycle.addObserver(lifeCycleObserver)
    }

    protected fun postSideEffect(event: BaseSideEffects) {
        sideEffectSubject.onNext(event)
    }

    protected fun reduce(reducer: STATE.() -> STATE) {
        synchronized(stateSubject) {
            stateSubject.onNext(stateSubject.value.reducer())
        }
    }

    protected fun <T : Any> Flowable<T>.handleSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        sideEffectSubject.onNext(BaseSideEffects.ShowLoadingIndicator)
        return subscribe({ result ->
            sideEffectSubject.onNext(BaseSideEffects.HideLoadingIndicator)
            onSuccess(result)
        }, { error ->
            onError(error)
        })
    }

    protected fun <T : Any> Flowable<T>.handleMutedSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        return subscribe(onSuccess, onError)
    }

    protected fun <T : Any> Observable<T>.handleMutedSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        return subscribe(onSuccess, onError)
    }

    protected fun <T : Any> Observable<T>.handleSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        sideEffectSubject.onNext(BaseSideEffects.ShowLoadingIndicator)
        return subscribe({ result ->
            sideEffectSubject.onNext(BaseSideEffects.HideLoadingIndicator)
            onSuccess(result)
        }, { error ->
            onError(error)
        })
    }

    protected fun <T : Any> Single<T>.handleSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        sideEffectSubject.onNext(BaseSideEffects.ShowLoadingIndicator)
        return subscribe({ result ->
            sideEffectSubject.onNext(BaseSideEffects.HideLoadingIndicator)
            onSuccess(result)
        }, { error ->
            onError(error)
        })
    }

    protected fun Completable.handleSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: () -> Unit
    ): Disposable {
        sideEffectSubject.onNext(BaseSideEffects.ShowLoadingIndicator)
        return subscribe({
            sideEffectSubject.onNext(BaseSideEffects.HideLoadingIndicator)
            onSuccess()
        }, { error ->
            onError(error)
        })
    }

    private fun handleError(throwable: Throwable) {
        sideEffectSubject.onNext(BaseSideEffects.HideLoadingIndicator)
        val sideEffect = BaseSideEffects.ShowMessage(throwable.localizedMessage)
        sideEffectSubject.onNext(sideEffect)
    }

    override fun onCleared() {
        dataSource.release()
        super.onCleared()
    }

}