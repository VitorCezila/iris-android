package com.ghn.iris.core.domain.models

import android.graphics.Bitmap

data class Comment(
    val id: String,
    val username: String,
    val profilePictureBitmap: Bitmap?,
    val formattedTime: String,
    val content: String,
    val isLiked: Boolean,
    val likeCount: Int
)
