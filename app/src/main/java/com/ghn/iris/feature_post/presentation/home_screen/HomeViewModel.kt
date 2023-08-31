package com.ghn.iris.feature_post.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.domain.use_case.GetOwnUserIdUseCase
import com.ghn.iris.core.presentation.PagingState
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.util.DefaultPaginator
import com.ghn.iris.core.util.Event
import com.ghn.iris.core.util.ParentType
import com.ghn.iris.core.util.PostLiker
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
class HomeViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val postLiker: PostLiker,
    private val getOwnUserId: GetOwnUserIdUseCase,
    private val profileUseCases: ProfileUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _pagingState = mutableStateOf<PagingState<Post>>(PagingState())
    val pagingState: State<PagingState<Post>> = _pagingState

    private val paginator = DefaultPaginator<Post>(
        onLoadUpdated = { isLoading ->
            _pagingState.value = pagingState.value.copy(
                isLoading = isLoading
            )
        },
        onRequest = { page ->
            postUseCases.getPostsForFollows(page = page)
        },
        onSuccess = { posts, firstPage ->
            _pagingState.value = pagingState.value.copy(
                items = if(firstPage) posts else pagingState.value.items + posts,
                endReached = posts.isEmpty(),
                isLoading = false
            )
        },
        onError = { uiText ->
            _eventFlow.emit(UiEvent.ShowSnackbar(uiText))
        }
    )

    init {
        loadProfile()
        loadInitialFeed()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LikedPost -> {
                toggleLikeForParent(event.postId)
            }

            is HomeEvent.DeletePost -> {
                deletePost(event.post.id)
            }
        }
    }

    private fun deletePost(postId: String) {
        viewModelScope.launch {
            when (val result = postUseCases.deletePost(postId)) {
                is Resource.Success -> {
                    _pagingState.value = pagingState.value.copy(
                        items = pagingState.value.items.filter {
                            it.id != postId
                        }
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(
                                R.string.successfully_deleted_post
                            )
                        )
                    )
                }

                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError())
                    )
                }
            }
        }
    }

    private fun loadProfile() {
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

    fun loadNextPosts() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun loadInitialFeed() {
        viewModelScope.launch {
            paginator.loadFirstItems()
        }
//        _pagingState.value = pagingState.value.copy(isLoading = true)
//        viewModelScope.launch {
//            val result = postUseCases.getPostsForFollows(0)
//            when(result) {
//                is Resource.Success -> {
//                    _pagingState.value = pagingState.value.copy(
//                        items = result.data ?: emptyList(),
//                        endReached = result.data?.isEmpty() ?: true
//                    )
//                }
//                is Resource.Error -> {
//                    _eventFlow.emit(UiEvent.ShowSnackbar(result.uiText ?: UiText.unknownError()))
//                }
//            }
//            _pagingState.value = pagingState.value.copy(isLoading = false)
//        }
    }

    private fun toggleLikeForParent(
        parentId: String,
    ) {
        viewModelScope.launch {
            postLiker.toggleLike(
                posts = pagingState.value.items,
                parentId = parentId,
                onRequest = { isLiked ->
                    postUseCases.toggleLikeForParent(
                        parentId = parentId,
                        parentType = ParentType.Post.type,
                        isLiked = isLiked
                    )
                },
                onStateUpdated = { posts ->
                    _pagingState.value = pagingState.value.copy(
                        items = posts
                    )
                }
            )
        }
    }
}