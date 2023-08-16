package com.ghn.iris.core.domain.models

data class Comment(
    val id: String,
    val username: String,
    val profilePictureBase64: String,
    val formattedTime: String,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
)
