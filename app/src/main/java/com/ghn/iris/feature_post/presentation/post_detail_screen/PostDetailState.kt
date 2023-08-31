package com.ghn.iris.feature_post.presentation.post_detail_screen

import com.ghn.iris.core.domain.models.Comment
import com.ghn.iris.core.domain.models.Post

data class PostDetailState(
    val post: Post? = null,
    val comments: List<Comment> = emptyList(),
    val isLoadingPost: Boolean = false,
    val isLoadingComments: Boolean = false
)
