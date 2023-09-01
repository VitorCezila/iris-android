package com.ghn.iris.feature_auth.data.remote

import com.ghn.iris.BuildConfig
import com.ghn.iris.core.data.dto.response.BasicApiResponse
import com.ghn.iris.feature_auth.data.remote.request.CreateAccountRequest
import com.ghn.iris.feature_auth.data.remote.request.LoginRequest
import com.ghn.iris.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("/user/authenticate")
    suspend fun authenticate()

    companion object {
        val BASE_URL_DESENV: String = BuildConfig.BASE_URL_DESENV
        val BASE_URL: String = BuildConfig.BASE_URL
    }
}