package com.ghn.iris.feature_auth.domain.models

import com.ghn.iris.core.util.SimpleResource
import com.ghn.iris.feature_auth.presentation.util.AuthError

data class RegisterResult(
    val emailError: AuthError? = null,
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
