package com.ghn.iris.feature_post.domain

import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.util.Resource
import com.ghn.iris.feature_post.domain.repository.PostRepository

class GetPostDetailsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<Post> {
        return repository.getPostDetails(postId)
    }
}