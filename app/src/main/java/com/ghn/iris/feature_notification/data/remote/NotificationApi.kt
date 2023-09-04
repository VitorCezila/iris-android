package com.ghn.iris.feature_notification.data.remote

import com.ghn.iris.BuildConfig
import com.ghn.iris.core.util.Constants
import com.ghn.iris.feature_notification.data.remote.dto.NotificationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NotificationApi {

    @GET("/notification/get")
    suspend fun getNotifications(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<NotificationDto>

    companion object {
        val BASE_URL_DESENV: String = BuildConfig.BASE_URL_DESENV
        val BASE_URL: String = BuildConfig.BASE_URL
    }

}