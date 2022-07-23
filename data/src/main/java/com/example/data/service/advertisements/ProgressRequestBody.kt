package com.example.data.service.advertisements

import com.example.domain.usecase.advertisement.IProgressListener
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import kotlin.concurrent.thread

class ProgressRequestBody(
    private val file: File,
    private val contentType: String,
    private val progressListener: IProgressListener,
    private val key: Any
) : RequestBody() {

    private var uploaded: Long = 0

    override fun contentType(): MediaType? = MediaType.parse(contentType)

    override fun writeTo(sink: BufferedSink) {
        val fileLength = file.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val inputStream = FileInputStream(file)
        try {
            while (true) {
                val read = inputStream.read(buffer)
                if (read == -1) break
                uploaded += read
                sink.write(buffer, 0, read)
                val progress = (((uploaded / fileLength.toDouble())) * 100).toInt()
                progressListener.onProgressUpdate(key, progress)
            }

        } catch (e: Exception) {
            progressListener.onError(key)
        } finally {
            inputStream.close()
            progressListener.onComplete(key)
        }
    }

    override fun contentLength(): Long = file.length()

}