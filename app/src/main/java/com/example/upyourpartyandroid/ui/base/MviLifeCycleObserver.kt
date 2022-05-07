package com.example.upyourpartyandroid.ui.base

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class MviLifeCycleObserver<STATE, SIDE_EFFECT>(
    private val stateSubject: BehaviorSubject<STATE>,
    private val sideEffectSubject: PublishSubject<SIDE_EFFECT>,
    private val onStateChange: (STATE) -> Unit,
    private val onSideEffect: (SIDE_EFFECT) -> Unit
) : DefaultLifecycleObserver {

    private var stateDisposable: Disposable? = null
    private var sideEffectDisposable: Disposable? = null

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        stateDisposable = stateSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onStateChange)

        sideEffectDisposable = sideEffectSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSideEffect)
    }

    override fun onPause(owner: LifecycleOwner) {
        stateDisposable?.dispose()
        sideEffectDisposable?.dispose()
        super.onPause(owner)
    }

}