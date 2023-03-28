package com.ghn.iris.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.presentation.ui.theme.DarkBlack
import com.ghn.iris.core.presentation.ui.theme.IconSizeMedium
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSize
import com.ghn.iris.core.presentation.ui.theme.SocialWhite
import com.ghn.iris.core.presentation.ui.theme.SpaceMedium
import com.ghn.iris.core.presentation.ui.theme.SpaceSmall

@Composable
fun UserProfileItem(
    user: UserItem,
    onActionItemClick: () -> Unit = {},
    actionIcon: @Composable () -> Unit = {},
    ownUserId: String = ""
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(DarkBlack)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = SpaceSmall,
                    horizontal = SpaceMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painterResource(id = R.drawable.sheep),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(ProfilePictureSize)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = SpaceSmall)
                    .weight(1f)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = SocialWhite
                )
                Text(
                    text = user.bio,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    color = SocialWhite,
                    modifier = Modifier.heightIn(
                        min = MaterialTheme.typography.body2.fontSize.value.dp * 3f
                    )
                )
            }
            if (user.userId != ownUserId) {
                IconButton(
                    onClick = onActionItemClick,
                    modifier = Modifier.size(IconSizeMedium)
                ) {
                    actionIcon()
                }
            }
        }
    }

}