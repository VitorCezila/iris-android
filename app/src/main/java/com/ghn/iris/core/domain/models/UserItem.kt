package com.ghn.iris.core.domain.models

import android.graphics.Bitmap

data class UserItem(
    val userId: String,
    val username: String,
    val profilePictureBitmap: Bitmap?,
    val bio: String,
    val isFollowing: Boolean
)
