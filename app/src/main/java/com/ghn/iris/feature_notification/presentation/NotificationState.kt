package com.ghn.iris.feature_notification.presentation

import com.ghn.iris.core.domain.models.Notification

data class NotificationState(
    val notifications: List<Notification> = emptyList(),
    val isLoading: Boolean = false
)
