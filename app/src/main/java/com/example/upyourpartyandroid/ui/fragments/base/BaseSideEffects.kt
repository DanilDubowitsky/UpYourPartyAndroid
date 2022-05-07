package com.example.upyourpartyandroid.ui.fragments.base

interface BaseSideEffects {

    class ShowMessage(val message: String): BaseSideEffects

    object ShowLoadingIndicator : BaseSideEffects

    object HideLoadingIndicator : BaseSideEffects

}