package com.ghn.iris.di

import com.ghn.iris.core.domain.use_case.DeletePost
import com.ghn.iris.feature_post.data.remote.PostApi
import com.ghn.iris.feature_post.data.repository.PostRepositoryImpl
import com.ghn.iris.feature_post.domain.CreateCommentUseCase
import com.ghn.iris.feature_post.domain.CreatePostUseCase
import com.ghn.iris.feature_post.domain.GetCommentsForPostUseCase
import com.ghn.iris.feature_post.domain.GetLikesForParentUseCase
import com.ghn.iris.feature_post.domain.GetPostDetailsUseCase
import com.ghn.iris.feature_post.domain.GetPostsForFollowsUseCase
import com.ghn.iris.feature_post.domain.PostUseCases
import com.ghn.iris.feature_post.domain.ToggleLikeForParentUseCase
import com.ghn.iris.feature_post.domain.repository.PostRepository
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
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostApi {
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PostApi.BASE_URL)
            .client(client)
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi,
        gson: Gson,
    ): PostRepository {
        return PostRepositoryImpl(api, gson)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollows = GetPostsForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository),
            getPostDetails = GetPostDetailsUseCase(repository),
            getCommentsForPost = GetCommentsForPostUseCase(repository),
            createComment = CreateCommentUseCase(repository),
            toggleLikeForParent = ToggleLikeForParentUseCase(repository),
            getLikesForParent = GetLikesForParentUseCase(repository),
            deletePost = DeletePost(repository)
        )
    }
}