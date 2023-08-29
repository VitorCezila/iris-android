package com.ghn.iris.di

import android.content.SharedPreferences
import com.ghn.iris.core.data.repository.ProfileRepositoryImpl
import com.ghn.iris.core.domain.repository.ProfileRepository
import com.ghn.iris.core.domain.use_case.ToggleFollowStateForUserUseCase
import com.ghn.iris.feature_post.data.remote.PostApi
import com.ghn.iris.feature_profile.data.remote.ProfileApi
import com.ghn.iris.feature_profile.domain.use_case.GetPostsForProfileUseCase
import com.ghn.iris.feature_profile.domain.use_case.GetProfileUseCase
import com.ghn.iris.feature_profile.domain.use_case.LogoutUseCase
import com.ghn.iris.feature_profile.domain.use_case.ProfileUseCases
import com.ghn.iris.feature_profile.domain.use_case.SearchUserUseCase
import com.ghn.iris.feature_profile.domain.use_case.UpdateProfileUseCase
import com.google.gson.Gson
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
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ProfileApi.BASE_URL)
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
        postApi: PostApi,
        gson: Gson,
        sharedPreferences: SharedPreferences
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, postApi, gson, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            updateProfile = UpdateProfileUseCase(repository),
            getPostsForProfile = GetPostsForProfileUseCase(repository),
            searchUser = SearchUserUseCase(repository),
            toggleFollowStateForUser = ToggleFollowStateForUserUseCase(repository),
            logout = LogoutUseCase(repository)
        )
    }
}