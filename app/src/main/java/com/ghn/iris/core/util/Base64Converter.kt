package com.ghn.iris.core.util

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import timber.log.Timber


fun String.base64ToImageBitmap(): ImageBitmap? {
    return try {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        val decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
         decodedBitmap.asImageBitmap()
    } catch (e: Exception) {
        Timber.e("Erro ao gerar bitmap: " + e.message)
        null
    }

}