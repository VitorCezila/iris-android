package com.ghn.iris.feature_post.data.remote

import com.ghn.iris.core.data.dto.response.BasicApiResponse
import com.ghn.iris.core.data.dto.response.UserItemDto
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.feature_post.data.remote.dto.CommentDto
import com.ghn.iris.feature_post.data.remote.dto.PostDto
import com.ghn.iris.feature_post.data.remote.request.CreateCommentRequest
import com.ghn.iris.feature_post.data.remote.request.LikeUpdateRequest
import okhttp3.MultipartBody
import retrofit2.http.*

interface PostApi {

    @GET("/post/get")
    suspend fun getPostsForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<PostDto>

    @GET("/user/posts")
    suspend fun getPostsForProfile(
        @Query("userId") userId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<PostDto>

    @Multipart
    @POST("/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postImage: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/post/details")
    suspend fun getPostDetails(
        @Query("postId") postId: String
    ): BasicApiResponse<PostDto>

    @GET("/comment/get")
    suspend fun getCommentsForPost(
        @Query("postId") postId: String
    ): List<CommentDto>

    @POST("/comment/create")
    suspend fun createComment(
        @Body request: CreateCommentRequest
    ): BasicApiResponse<Unit>

    @POST("/like")
    suspend fun likeParent(
        @Body request: LikeUpdateRequest
    ): BasicApiResponse<Unit>

    @DELETE("/unlike")
    suspend fun unlikeParent(
        @Query("parentId") parentId: String,
        @Query("parentType") parentType: Int
    ): BasicApiResponse<Unit>

    @GET("/like/parent")
    suspend fun getLikesForParent(
        @Query("parentId") parentId: String
    ): List<UserItemDto>

    @DELETE("/post/delete")
    suspend fun deletePost(
        @Query("postId") postId: String,
    )

    companion object {
        const val BASE_URL = "http://192.168.15.20:8080"
    }
}