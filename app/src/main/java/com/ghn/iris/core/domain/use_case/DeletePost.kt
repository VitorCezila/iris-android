package com.ghn.iris.core.domain.use_case

import com.ghn.iris.core.util.SimpleResource
import com.ghn.iris.feature_post.domain.repository.PostRepository

class DeletePost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): SimpleResource {
        return repository.deletePost(postId)
    }
}