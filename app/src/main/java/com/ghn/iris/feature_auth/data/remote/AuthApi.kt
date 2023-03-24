package com.ghn.iris.feature_auth.data.remote

import com.ghn.iris.core.data.dto.response.BasicApiResponse
import com.ghn.iris.feature_auth.data.remote.request.CreateAccountRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    companion object {
        const val BASE_URL = "http://192.168.15.20:8080"
    }
}