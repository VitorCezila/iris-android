package com.ghn.iris.core.data.dto.response

import com.ghn.iris.core.domain.models.UserItem

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
            profilePictureBase64 = profilePictureBase64,
            bio = bio,
            isFollowing = isFollowing
        )
    }
}
