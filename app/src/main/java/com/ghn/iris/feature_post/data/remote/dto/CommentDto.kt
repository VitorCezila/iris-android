package com.ghn.iris.feature_post.data.remote.dto

import com.ghn.iris.core.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.*

data class CommentDto(
    val id: String,
    val username: String,
    val profilePictureBase64: String,
    val timestamp: Long,
    val content: String,
    val isLiked: Boolean,
    val likeCount: Int
) {
    fun toComment(): Comment {
        return Comment(
            id = id,
            username = username,
            profilePictureBase64 = profilePictureBase64,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            },
            content = content,
            isLiked = isLiked,
            likeCount = likeCount
        )
    }
}
