package com.ghn.iris.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.presentation.ui.theme.DarkBlack
import com.ghn.iris.core.presentation.ui.theme.LightGray
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSize
import com.ghn.iris.core.presentation.ui.theme.SocialPink
import com.ghn.iris.core.presentation.ui.theme.SocialWhite
import com.ghn.iris.core.presentation.ui.theme.White
import com.ghn.iris.core.util.base64ToImageBitmap

@Composable
fun Post(
    post: Post,
    onPostClicked: () -> Unit = {},
    onLikeClicked: () -> Unit = {},
    onCommentClicked: () -> Unit = {},
    onShareClicked: () -> Unit = {},
    onUserClicked: () -> Unit = {},
    onSaveClicked: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {

    val profilePictureBitMap = post.profilePictureBase64?.base64ToImageBitmap()
    val postImageBitMap = post.imageBase64?.base64ToImageBitmap()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(DarkBlack)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(profilePictureBitMap != null) {
                Image(
                    bitmap = profilePictureBitMap,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(ProfilePictureSize)
                        .clip(RoundedCornerShape(25.dp))
                        .clickable { onUserClicked() }
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = post.username,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = SocialWhite,
                    modifier = Modifier.clickable { onUserClicked() }
                )
                Text(
                    text = post.formattedTime,
                    style = MaterialTheme.typography.caption,
                    color = LightGray,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onPostClicked()
            }
        ) {
            Text(
                text = post.content,
                style = MaterialTheme.typography.body1,
                color = SocialWhite
            )
            Spacer(modifier = Modifier.height(16.dp))
            if(postImageBitMap != null) {
                Image(
                    bitmap = postImageBitMap,
                    contentDescription = "Post image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PostAction(
                icon = Icons.Default.Favorite,
                count = post.likeCount,
                isSelected = post.isLiked,
                onClick = onLikeClicked,
            )
            Spacer(modifier = Modifier.width(16.dp))
            PostAction(
                icon = Icons.Default.ChatBubble,
                count = post.commentCount,
                onClick = onCommentClicked,
            )
            Spacer(modifier = Modifier.width(16.dp))
            PostAction(
                icon = Icons.Default.Share,
                count = post.sharesCount,
                onClick = onShareClicked,
            )
            Spacer(modifier = Modifier.weight(1f))
            PostAction(
                icon = if (post.isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                count = null,
                isSelected = post.isSaved,
                onClick = onSaveClicked,
            )
        }
    }
}

@Composable
fun PostAction(
    icon: ImageVector,
    count: Int?,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(36.dp)
                .wrapContentWidth(),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) SocialPink else MaterialTheme.colors.onSurface,
                modifier = Modifier.size(18.dp),
            )
            if (count != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.SemiBold,
                    color = White
                )
            }
        }
    }
}