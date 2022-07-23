package com.example.upyourpartyandroid.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.upyourpartyandroid.ui.fragments.base.BaseSideEffects
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BaseMVIViewModel<STATE, WG : IRxWorkGroup>(
    protected val dataSource: WG
) : ViewModel() {

    private val initialState : STATE by lazy { createInitialState() }

    abstract fun createInitialState() : STATE

    protected val currentState get() = stateSubject.value

    private val stateSubject: BehaviorSubject<STATE> = BehaviorSubject.createDefault(initialState)

    private val sideEffectSubject: PublishSubject<BaseSideEffects> = PublishSubject.create()

    private val lifeCycleObserver by lazy { MviLifeCycleObserver<STATE, BaseSideEffects>() }

    fun observe(
        lifecycleOwner: LifecycleOwner,
        onStateChange: (STATE) -> Unit,
        onSideEffect: (BaseSideEffects) -> Unit
    ) {
        lifeCycleObserver.apply {
            setOnSideEffect(onSideEffect)
            setOnStateChange(onStateChange)
            setSideEffectSubject(sideEffectSubject)
            setStateSubject(stateSubject)
        }
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

    protected fun Completable.handleMutedSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: () -> Unit
    ): Disposable {
        return subscribe(onSuccess, onError)
    }

    protected fun <T : Any> Single<T>.handleMutedSubscribe(
        onError: (Throwable) -> Unit = ::handleError,
        onSuccess: (T) -> Unit
    ): Disposable {
        return subscribe({ result ->
            onSuccess(result)
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