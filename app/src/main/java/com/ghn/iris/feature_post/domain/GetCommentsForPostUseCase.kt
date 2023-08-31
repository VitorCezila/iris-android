package com.ghn.iris.feature_post.domain

import com.ghn.iris.core.domain.models.Comment
import com.ghn.iris.core.util.Resource
import com.ghn.iris.feature_post.domain.repository.PostRepository

class GetCommentsForPostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<List<Comment>> {
        return repository.getCommentsForPost(postId)
    }
}