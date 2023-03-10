package com.ghn.iris.presentation.create_post_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ghn.iris.presentation.util.states.StandardTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(

) : ViewModel() {

    private val _content = mutableStateOf(StandardTextFieldState());
    val content: State<StandardTextFieldState> = _content

    fun setContent(content: StandardTextFieldState) {
        _content.value = content
    }


}