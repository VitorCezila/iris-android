package com.ghn.iris.feature_notification.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ghn.iris.core.domain.models.Notification
import com.ghn.iris.core.util.Constants
import com.ghn.iris.feature_notification.data.paging.NotificationSource
import com.ghn.iris.feature_notification.data.remote.NotificationApi
import com.ghn.iris.feature_notification.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class NotificationRepositoryImpl(
    private val api: NotificationApi
): NotificationRepository {

    override val notifications: Flow<PagingData<Notification>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            NotificationSource(api)
        }.flow
}