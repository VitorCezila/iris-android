package com.ghn.iris.feature_post.presentation.create_post_screen

import android.net.Uri

sealed class CreatePostEvent {
    data class EnterContent(val value: String) : CreatePostEvent()
    data class PickImage(val uri: Uri?): CreatePostEvent()
    data class CropImage(val uri: Uri?): CreatePostEvent()
    object CreatePost: CreatePostEvent()
}
