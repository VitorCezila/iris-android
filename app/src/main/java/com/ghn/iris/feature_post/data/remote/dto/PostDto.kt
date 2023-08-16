package com.ghn.iris.feature_post.data.remote.dto

import com.ghn.iris.core.domain.models.Post
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

data class PostDto(
    val id: String,
    val userId: String,
    val username: String,
    val profilePictureBase64: String?,
    val content: String?,
    val imageBase64: String?,
    val timestamp: Long,
    val likeCount: Int,
    val commentCount: Int,
    val isLiked: Boolean,
    val isOwnPost: Boolean
) {
    fun toPost(): Post {
        Timber.d("Post Received: $this")
        return Post(
            id = id,
            userId = userId,
            username = username,
            profilePictureBase64 = profilePictureBase64,
            content = content ?: "",
            imageBase64 = imageBase64,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            },
            likeCount = likeCount,
            commentCount = commentCount,
            sharesCount = 0,
            isLiked = isLiked,
            isSaved = false,
            isOwnPost = isOwnPost
        )
    }
}
