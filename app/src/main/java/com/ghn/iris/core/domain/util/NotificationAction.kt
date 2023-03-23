package com.ghn.iris.core.domain.util

sealed class NotificationAction(val type: Int) {
    object LikedPost : NotificationAction(0)
    object LikedComment : NotificationAction(1)
    object CommentedOnPost : NotificationAction(2)
    object FollowedUser: NotificationAction(3)
}
