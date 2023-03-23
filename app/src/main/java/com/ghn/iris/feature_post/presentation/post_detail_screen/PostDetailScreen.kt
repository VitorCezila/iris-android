package com.ghn.iris.feature_post.presentation.post_detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ChatBubble
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.domain.models.Comment
import com.ghn.iris.core.domain.models.Post
import com.ghn.iris.core.presentation.components.PostAction
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.feature_post.presentation.post_detail_screen.components.Comment
import com.ghn.iris.core.presentation.util.Screen
import com.ghn.iris.core.util.Constants

@Composable
fun PostDetailScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    post: Post
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = stringResource(id = R.string.post),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            showBackArrow = true
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            item {
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
                                .size(Constants.ProfilePictureSize)
                                .clip(RoundedCornerShape(25.dp))
                                .clickable {
                                    onNavigate(Screen.ProfileScreen.route)
                                },
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
                                modifier = Modifier.clickable { onNavigate(Screen.ProfileScreen.route) }
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

                    Column(modifier = Modifier.fillMaxWidth()) {
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
                            onClick = {},
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PostAction(
                            icon = Icons.Outlined.ChatBubble,
                            count = post.commentCount,
                            onClick = {},
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        PostAction(
                            icon = Icons.Outlined.Share,
                            count = post.sharesCount,
                            onClick = {},
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        PostAction(
                            icon = if (post.isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            count = null,
                            isSelected = post.isSaved,
                            onClick = {},
                        )
                    }
                    Divider(color = DarkGray, thickness = 3.dp)
                    Spacer(modifier = Modifier.height(SpaceLarge))

                    Text(
                        text = "COMMENTS (${post.commentCount})",
                        modifier = Modifier.fillMaxWidth()
                    )

                }

            }
            items(post.commentCount) {
                Comment(
                    comment = Comment(
                        id = "0",
                        username = "Sheep",
                        profilePictureUrl = "",
                        formattedTime = "2m ago",
                        comment = "Oh so cute!",
                        isLiked = true,
                        likeCount = 20
                    )
                )
            }
        }
    }
}