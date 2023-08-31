package com.ghn.iris.feature_post.domain

import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.util.Constants
import com.ghn.iris.core.util.Resource
import com.ghn.iris.feature_post.domain.repository.PostRepository

class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Post>> {
        return repository.getPostsForFollows(page, pageSize)
    }
}