package com.ghn.iris.feature_notification.presentation

sealed class NotificationEvent {
    data class ClickedOnUser(val userId: String): NotificationEvent()
    data class ClickedOnNotification(val userId: String): NotificationEvent()
}
