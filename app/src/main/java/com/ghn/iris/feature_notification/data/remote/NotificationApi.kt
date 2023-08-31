package com.ghn.iris.feature_notification.data.remote

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
        const val BASE_URL = "http://192.168.15.20:8080/"
    }

}