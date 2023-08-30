package com.ghn.iris.feature_notification.domain.use_case

import androidx.paging.PagingData
import com.ghn.iris.core.domain.models.Notification
import com.ghn.iris.feature_notification.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase(
    private val repository: NotificationRepository
) {

    operator fun invoke(): Flow<PagingData<Notification>> {
        return repository.notifications
    }

}