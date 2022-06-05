package com.example.upyourpartyandroid.ui.helpers

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore
import java.io.File
import javax.inject.Inject


class FilesHelper @Inject constructor(
    private val context: Context
) {

    fun createImageFile(selectedImage: Uri): File {
        var filePath = ""
        val fileId = DocumentsContract.getDocumentId(selectedImage)
        val id = fileId.split(":").toTypedArray()[1]
        val column = arrayOf(MediaStore.Images.Media.DATA)
        val selector = MediaStore.Images.Media._ID + "=?"
        val cursor: Cursor? = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            column, selector, arrayOf(id), null
        )
        val columnIndex: Int = cursor!!.getColumnIndex(column[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return File(filePath)
    }

    fun getMimeType(fileUri: Uri): String =
        context.contentResolver.getType(fileUri) ?: DEFAULT_MIME_TYPE

    companion object {
        const val DEFAULT_MIME_TYPE = "image/*"
    }

}