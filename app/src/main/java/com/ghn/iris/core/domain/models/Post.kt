package com.ghn.iris.core.domain.models

import android.graphics.Bitmap

data class Post(
    val id: String,
    val userId: String,
    val username: String,
    val profilePictureBitmap: Bitmap?,
    val content: String,
    val imageBitmap: Bitmap?,
    val formattedTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val sharesCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val isOwnPost: Boolean
)
