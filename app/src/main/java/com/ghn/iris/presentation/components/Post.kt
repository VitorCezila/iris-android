package com.ghn.iris.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.domain.Post
import com.ghn.iris.presentation.ui.theme.*

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
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(DarkBlack)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = R.drawable.cezila),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .clickable { onUserClicked },
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = post.username,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = SocialWhite,
                    modifier = Modifier.clickable { onUserClicked }
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
                onPostClicked
            }
        ) {
            Text(
                text = post.content,
                style = MaterialTheme.typography.body1,
                color = SocialWhite
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painterResource(id = R.drawable.sheep),
                contentDescription = "Post image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
            )
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
private fun PostAction(
    icon: ImageVector,
    count: Int?,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .height(36.dp)
            .wrapContentWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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