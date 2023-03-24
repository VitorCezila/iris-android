package com.ghn.iris.core.domain.states

import com.ghn.iris.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
