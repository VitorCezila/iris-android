package com.ghn.iris.core.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import java.io.InputStream

fun Context.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(null, InputMethodManager.SHOW_FORCED)
}

fun Context.loadBitmapFromUri(uri: Uri): Bitmap? {
    var bitmap: Bitmap? = null
    val inputStream: InputStream? = contentResolver.openInputStream(uri)
    if (inputStream != null) {
        bitmap = BitmapFactory.decodeStream(inputStream)
    }
    inputStream?.close()
    return bitmap
}