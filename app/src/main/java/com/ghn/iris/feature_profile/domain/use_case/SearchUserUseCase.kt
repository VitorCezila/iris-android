package com.ghn.iris.feature_profile.domain.use_case

import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.domain.repository.ProfileRepository
import com.ghn.iris.core.util.Resource

class SearchUserUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(query: String): Resource<List<UserItem>> {
        if(query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return repository.searchUser(query)
    }

}
