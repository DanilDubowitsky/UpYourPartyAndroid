package com.example.data.helpers

import android.os.Handler
import android.os.Looper
import com.example.domain.usecase.advertisement.IProgressListener
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

class UploadProgressListener @Inject constructor(): IProgressListener {

    private val listeners: HashMap<Any, (progress: Int) -> Unit> = HashMap()

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onProgressUpdate(key: Any, progress: Int) {
        mainHandler.post {
            listeners[key]?.invoke(progress)
        }
    }

    override fun setProgressListener(key: Any, onProgressUpdate: (progress: Int) -> Unit) {
        listeners[key] = onProgressUpdate
    }

    override fun removeProgressListener(key: Any) {
        listeners.remove(key)
    }

    override fun onComplete(key: Any) {
        mainHandler.post {
            listeners.remove(key)?.invoke(100)
        }
    }

    override fun onError(key: Any) {
        mainHandler.post {
            listeners.remove(key)?.invoke(-1)
        }
    }

    override fun release() {
        listeners.clear()
    }

}