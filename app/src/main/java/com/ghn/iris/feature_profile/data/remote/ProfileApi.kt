package com.ghn.iris.feature_profile.data.remote

import com.ghn.iris.BuildConfig
import com.ghn.iris.core.data.dto.response.BasicApiResponse
import com.ghn.iris.core.data.dto.response.UserItemDto
import com.ghn.iris.feature_profile.data.remote.request.FollowUpdateRequest
import com.ghn.iris.feature_profile.data.remote.response.ProfileResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface ProfileApi {

    @GET("/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    @Multipart
    @PUT("/user/update")
    suspend fun updateProfile(
        @Part bannerImage: MultipartBody.Part?,
        @Part profilePicture: MultipartBody.Part?,
        @Part updateProfileData: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/user/search")
    suspend fun searchUser(
        @Query("query") query: String
    ): List<UserItemDto>

    @POST("/following/follow")
    suspend fun followUser(
        @Body request: FollowUpdateRequest
    ): BasicApiResponse<Unit>

    @DELETE("/following/unfollow")
    suspend fun unfollowUser(
        @Query("userId") userId: String
    ): BasicApiResponse<Unit>

    companion object {
        val BASE_URL_DESENV: String = BuildConfig.BASE_URL_DESENV
        val BASE_URL: String = BuildConfig.BASE_URL
    }
}