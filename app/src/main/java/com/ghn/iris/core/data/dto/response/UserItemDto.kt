package com.ghn.iris.core.data.dto.response

import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.util.base64ToImageBitmap

data class UserItemDto(
    val userId: String,
    val username: String,
    val profilePictureBase64: String,
    val bio: String,
    val isFollowing: Boolean
) {
    fun toUserItem(): UserItem {
        return UserItem(
            userId = userId,
            username = username,
            profilePictureBitmap = profilePictureBase64.base64ToImageBitmap(),
            bio = bio,
            isFollowing = isFollowing
        )
    }
}
