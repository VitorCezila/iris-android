package com.ghn.iris.feature_post.presentation.post_detail_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.Comment
import com.ghn.iris.core.presentation.ui.theme.DarkBlack
import com.ghn.iris.core.presentation.ui.theme.LightGray
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSize
import com.ghn.iris.core.presentation.ui.theme.SocialPink
import com.ghn.iris.core.presentation.ui.theme.SocialWhite
import com.ghn.iris.core.presentation.ui.theme.SpaceMedium

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment,
    onLikeClick: (Boolean) -> Unit = {},
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
                painterResource(id = R.drawable.sheep),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(ProfilePictureSize)
                    .clip(RoundedCornerShape(25.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = comment.username,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = SocialWhite
                )
                Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.body2,
                    color = SocialWhite
                )
                Row {
                    Text(
                        text = comment.formattedTime,
                        style = MaterialTheme.typography.body2,
                        color = LightGray
                    )
                    Spacer(modifier = Modifier.width(SpaceMedium))
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.body2,
                        color = LightGray
                    )
                    Spacer(modifier = Modifier.width(SpaceMedium))
                    Text(
                        text = "${comment.likeCount} Likes",
                        style = MaterialTheme.typography.body2,
                        color = LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onLikeClick(comment.isLiked)
                },
                modifier = Modifier
                    .size(18.dp),
            ) {
                Icon(
                    imageVector = if (comment.isLiked) {
                        Icons.Default.Favorite
                    } else Icons.Rounded.FavoriteBorder,
                    tint = if (comment.isLiked) {
                        SocialPink
                    } else LightGray,
                    contentDescription = if (comment.isLiked) {
                        stringResource(id = R.string.unlike)
                    } else stringResource(id = R.string.like)
                )
            }
        }
    }
}