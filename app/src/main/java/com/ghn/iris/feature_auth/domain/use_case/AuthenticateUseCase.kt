package com.ghn.iris.feature_auth.domain.use_case

import com.ghn.iris.core.util.SimpleResource
import com.ghn.iris.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }

}