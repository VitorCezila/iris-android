package com.ghn.iris.feature_post.presentation.create_post_screen

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghn.iris.R
import com.ghn.iris.core.domain.states.StandardTextFieldState
import com.ghn.iris.core.domain.use_case.GetOwnUserIdUseCase
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.util.Resource
import com.ghn.iris.core.util.UiText
import com.ghn.iris.feature_post.domain.PostUseCases
import com.ghn.iris.feature_profile.domain.use_case.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val getOwnUserId: GetOwnUserIdUseCase,
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _state = mutableStateOf(CreatePostState())
    val state: State<CreatePostState> = _state

    private val _contentState = mutableStateOf(StandardTextFieldState());
    val contentState: State<StandardTextFieldState> = _contentState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _chosenImageUri = mutableStateOf<Uri?>(null)
    val chosenImageUri: State<Uri?> = _chosenImageUri

    fun onEvent(event: CreatePostEvent) {
        when (event) {
            is CreatePostEvent.EnterContent -> {
                _contentState.value = contentState.value.copy(
                    text = event.value
                )
            }
            is CreatePostEvent.PickImage -> {
                _chosenImageUri.value = event.uri
            }
            is CreatePostEvent.CropImage -> {
                _chosenImageUri.value = event.uri
            }
            is CreatePostEvent.CreatePost -> {
                viewModelScope.launch {
                    _isLoading.value = true
                    val result = postUseCases.createPostUseCase(
                        content = contentState.value.text,
                        imageUri = chosenImageUri.value
                    )
                    when (result) {
                        is Resource.Success -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    uiText = UiText.StringResource(R.string.post_created)
                                )
                            )
                            _eventFlow.emit(UiEvent.NavigateUp)
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                    }
                    _isLoading.value = false
                }
            }
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            val result = profileUseCases.getProfile(
                getOwnUserId.invoke()
            )
            when(result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        profile = result.data
                    )
                }
                else -> {}
            }
        }
    }


}