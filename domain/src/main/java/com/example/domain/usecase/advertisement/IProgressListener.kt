package com.example.domain.usecase.advertisement

interface IProgressListener {

    fun onProgressUpdate(key: Any, progress: Int)

    fun setProgressListener(key: Any, onProgressUpdate: (progress: Int) -> Unit)

    fun removeProgressListener(key: Any)

    fun onComplete(key: Any)

    fun onError(key: Any)

    fun release()

}