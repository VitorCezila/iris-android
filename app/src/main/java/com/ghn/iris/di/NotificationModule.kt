package com.ghn.iris.di

import com.ghn.iris.feature_notification.data.remote.NotificationApi
import com.ghn.iris.feature_notification.data.repository.NotificationRepositoryImpl
import com.ghn.iris.feature_notification.domain.repository.NotificationRepository
import com.ghn.iris.feature_notification.domain.use_case.GetNotificationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideNotificationApi(client: OkHttpClient): NotificationApi {
        return Retrofit.Builder()
            .baseUrl(NotificationApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotificationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNotificationRepository(api: NotificationApi): NotificationRepository {
        return NotificationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetNotificationsUseCase(repository: NotificationRepository): GetNotificationsUseCase {
        return GetNotificationsUseCase(repository)
    }

}