package com.ghn.iris.core.domain.models

data class Post(
    val id: String,
    val userId: String,
    val username: String,
    val profilePictureBase64: String?,
    val content: String,
    val imageBase64: String?,
    val formattedTime: String,
    val likeCount: Int,
    val commentCount: Int,
    val sharesCount: Int,
    val isLiked: Boolean,
    val isSaved: Boolean,
    val isOwnPost: Boolean
)
