package com.ghn.iris.feature_profile.domain.use_case

import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.domain.repository.ProfileRepository
import com.ghn.iris.core.util.Resource

class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String, page: Int): Resource<List<Post>> {
        return repository.getPostsPaged(
            userId = userId,
            page = page
        )
    }

}
