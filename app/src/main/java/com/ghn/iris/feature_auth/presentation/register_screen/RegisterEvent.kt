package com.ghn.iris.feature_auth.presentation.register_screen

sealed class RegisterEvent {
    data class EnteredUsername(val value: String) : RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    object TogglePasswordVisiblity : RegisterEvent()
    object Register : RegisterEvent()
}
