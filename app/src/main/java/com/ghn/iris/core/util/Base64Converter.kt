package com.ghn.iris.core.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import timber.log.Timber
import java.io.ByteArrayInputStream

fun String.base64ToImageBitmap(): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        val inputStream = ByteArrayInputStream(decodedBytes)
        return BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        Timber.e("Error converting base64 to bitmap: " + e.message)
        null
    }

}