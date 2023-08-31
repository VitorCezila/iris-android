package com.ghn.iris.core.domain.models

import com.ghn.iris.feature_notification.domain.NotificationType

data class Notification(
    val userId: String,
    val parentId: String,
    val username: String,
    val notificationType: NotificationType,
    val formattedTime: String
)
