package com.ghn.iris.feature_notification.data.remote.dto

import com.ghn.iris.core.domain.models.Notification
import com.ghn.iris.feature_notification.domain.NotificationType
import java.text.SimpleDateFormat
import java.util.Locale

data class NotificationDto(
    val timestamp: Long,
    val userId: String,
    val parentId: String,
    val type: Int,
    val username: String,
    val id: String
) {
    fun toNotification(): Notification {
        return Notification(
            userId = userId,
            parentId = parentId,
            username = username,
            notificationType = when(type) {
                NotificationType.FollowedUser.type -> NotificationType.FollowedUser
                NotificationType.LikedPost.type -> NotificationType.LikedPost
                NotificationType.LikedComment.type -> NotificationType.LikedComment
                NotificationType.CommentedOnPost.type -> NotificationType.CommentedOnPost
                else -> NotificationType.FollowedUser
            },
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            }
        )
    }
}
