package com.ghn.iris.feature_post.domain

import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.util.Resource
import com.ghn.iris.feature_post.domain.repository.PostRepository

class GetLikesForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(parentId: String): Resource<List<UserItem>> {
        return repository.getLikesForParent(parentId)
    }
}