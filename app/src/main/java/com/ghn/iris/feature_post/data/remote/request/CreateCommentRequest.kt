package com.ghn.iris.feature_post.data.remote.request

data class CreateCommentRequest(
    val content: String,
    val postId: String,
)
