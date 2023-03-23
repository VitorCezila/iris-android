package com.ghn.iris.core.domain.models

import com.ghn.iris.core.domain.util.NotificationAction

data class Notification(
    val userId: String,
    val parentId: String,
    val username: String,
    val notificationType: NotificationAction,
    val formattedTime: String
)
