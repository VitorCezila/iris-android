package com.ghn.iris.presentation.edit_profile_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ghn.iris.presentation.util.states.StandardTextFieldState
import javax.inject.Inject

class EditProfileViewModel @Inject constructor(

): ViewModel()  {

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    fun setUsernameState(state: StandardTextFieldState) {
        _usernameState.value = state
    }

    private val _bioState = mutableStateOf(StandardTextFieldState())
    val bioState: State<StandardTextFieldState> = _bioState
    fun setBioState(state: StandardTextFieldState) {
        _bioState.value = state
    }


}