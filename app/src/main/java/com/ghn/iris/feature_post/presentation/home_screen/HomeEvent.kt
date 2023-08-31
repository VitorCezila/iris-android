package com.ghn.iris.feature_post.presentation.home_screen

import com.ghn.iris.core.domain.models.Post

sealed class HomeEvent {
    data class LikedPost(val postId: String): HomeEvent()
    data class DeletePost(val post: Post): HomeEvent()
}
