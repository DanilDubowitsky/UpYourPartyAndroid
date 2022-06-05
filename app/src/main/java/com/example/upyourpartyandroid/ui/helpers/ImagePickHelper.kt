package com.example.upyourpartyandroid.ui.helpers

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ImagePickHelper(private val registry : ActivityResultRegistry) : DefaultLifecycleObserver {

    lateinit var getContent : ActivityResultLauncher<String>

    private var mimeType = "image/*"

    private var onResult: ((Uri?) -> Unit)? = null

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { imageUri: Uri? ->
            onResult?.invoke(imageUri)
        }
    }

    fun setOnResultListener(onResult: (Uri?) -> Unit) {
        this.onResult = onResult
    }

    fun selectImage() {
        getContent.launch(mimeType)
    }

}