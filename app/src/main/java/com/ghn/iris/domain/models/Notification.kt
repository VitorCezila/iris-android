package com.ghn.iris.domain.models

import com.ghn.iris.domain.util.NotificationAction

data class Notification(
    val userId: String,
    val parentId: String,
    val username: String,
    val notificationType:  NotificationAction,
    val formattedTime: String
)
