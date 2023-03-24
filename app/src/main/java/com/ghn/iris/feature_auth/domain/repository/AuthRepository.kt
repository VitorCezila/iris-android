package com.ghn.iris.feature_auth.domain.repository

import com.ghn.iris.core.util.SimpleResource

interface AuthRepository {

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResource

}