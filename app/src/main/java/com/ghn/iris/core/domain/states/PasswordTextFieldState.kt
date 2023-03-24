package com.ghn.iris.core.domain.states

import com.ghn.iris.core.util.Error

data class PasswordTextFieldState(
    val text: String = "",
    val error: Error? = null,
    val isPasswordVisible: Boolean = false
)
