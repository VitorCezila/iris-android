package com.ghn.iris.feature_post.domain

import android.net.Uri
import com.ghn.iris.R
import com.ghn.iris.core.util.Resource
import com.ghn.iris.core.util.SimpleResource
import com.ghn.iris.core.util.UiText
import com.ghn.iris.feature_post.domain.repository.PostRepository

class CreatePostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        description: String,
        imageUri: Uri?
    ): SimpleResource {
        if(imageUri == null) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_no_image_picked)
            )
        }
        if(description.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_description_blank)
            )
        }
        return repository.createPost(description, imageUri)
    }
}