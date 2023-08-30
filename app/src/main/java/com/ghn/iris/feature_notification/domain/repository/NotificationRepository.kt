package com.ghn.iris.feature_notification.domain.repository

import androidx.paging.PagingData
import com.ghn.iris.core.domain.models.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    val notifications: Flow<PagingData<Notification>>

}