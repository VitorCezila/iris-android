package com.ghn.iris.feature_notification.domain

sealed class NotificationType(val type: Int) {
    object LikedPost: NotificationType(0)
    object LikedComment : NotificationType(1)
    object CommentedOnPost : NotificationType(2)
    object FollowedUser : NotificationType(3)
}
