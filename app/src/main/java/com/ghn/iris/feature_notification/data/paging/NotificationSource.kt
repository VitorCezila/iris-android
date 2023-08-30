package com.ghn.iris.feature_notification.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ghn.iris.core.domain.models.Notification
import com.ghn.iris.core.util.Constants
import com.ghn.iris.feature_notification.data.remote.NotificationApi
import retrofit2.HttpException
import java.io.IOException

class NotificationSource(
    private val api: NotificationApi
): PagingSource<Int, Notification>() {

    private var currentPage = 0

    override fun getRefreshKey(state: PagingState<Int, Notification>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Notification> {
        return try {
            val nextPage = params.key ?: currentPage
            val notifications = api.getNotifications(
                page = nextPage,
                pageSize = Constants.DEFAULT_PAGE_SIZE
            )
            LoadResult.Page(
                data = notifications.map { it.toNotification() },
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = if (notifications.isEmpty()) null else currentPage + 1
            ).also { currentPage++ }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}