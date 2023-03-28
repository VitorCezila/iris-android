package com.ghn.iris.feature_post.domain.repository

import android.net.Uri
import com.ghn.iris.core.domain.models.Comment
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.util.Resource
import com.ghn.iris.core.util.SimpleResource

interface PostRepository {

    suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>>

    suspend fun createPost(description: String, imageUri: Uri): SimpleResource

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>

    suspend fun createComment(postId: String, comment: String): SimpleResource

    suspend fun likeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun unlikeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun getLikesForParent(parentId: String): Resource<List<UserItem>>

    suspend fun deletePost(postId: String): SimpleResource
}