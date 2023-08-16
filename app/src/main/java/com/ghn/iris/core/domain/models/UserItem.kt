package com.ghn.iris.core.domain.models

data class UserItem(
    val userId: String,
    val username: String,
    val profilePictureBase64: String,
    val bio: String,
    val isFollowing: Boolean
)
