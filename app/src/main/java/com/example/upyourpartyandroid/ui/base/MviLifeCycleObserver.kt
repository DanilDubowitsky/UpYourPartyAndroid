package com.example.upyourpartyandroid.ui.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class MviLifeCycleObserver<STATE, SIDE_EFFECT> : DefaultLifecycleObserver {

    private var stateDisposable: Disposable? = null
    private var sideEffectDisposable: Disposable? = null

    private var stateSubject: BehaviorSubject<STATE>? = null
    private var sideEffectSubject: PublishSubject<SIDE_EFFECT>? = null
    private var onStateChange: ((STATE) -> Unit)? = null
    private var onSideEffect: ((SIDE_EFFECT) -> Unit)? = null

    fun setStateSubject(stateSubject: BehaviorSubject<STATE>) {
        this.stateSubject = stateSubject
    }

    fun setSideEffectSubject(sideEffectSubject: PublishSubject<SIDE_EFFECT>) {
        this.sideEffectSubject = sideEffectSubject
    }

    fun setOnStateChange(onStateChange: (STATE) -> Unit) {
        this.onStateChange = onStateChange
    }

    fun setOnSideEffect(onSideEffect: (SIDE_EFFECT) -> Unit) {
        this.onSideEffect = onSideEffect
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        stateDisposable = stateSubject
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(onStateChange)

        sideEffectDisposable = sideEffectSubject
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(onSideEffect)
    }

    override fun onPause(owner: LifecycleOwner) {
        stateDisposable?.dispose()
        sideEffectDisposable?.dispose()
        super.onPause(owner)
    }

}