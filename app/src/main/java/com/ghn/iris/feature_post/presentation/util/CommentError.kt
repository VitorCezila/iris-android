package com.ghn.iris.feature_post.presentation.util

import com.ghn.iris.core.util.Error

sealed class CommentError : Error() {
    object FieldEmpty: CommentError()
}
