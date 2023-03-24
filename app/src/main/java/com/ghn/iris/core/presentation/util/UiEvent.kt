package com.ghn.iris.core.presentation.util

import com.ghn.iris.core.util.Event
import com.ghn.iris.core.util.UiText

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin: UiEvent()
}
