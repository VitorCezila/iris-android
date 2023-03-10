package com.ghn.iris.presentation.profile_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghn.iris.R
import com.ghn.iris.domain.models.User
import com.ghn.iris.presentation.ui.theme.*

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    isFollowing: Boolean = true,
    isOwnProfile: Boolean = true,
    profilePictureSize: Dp,
    onEditClick: () -> Unit = {},
    onFollowClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onMessageClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = -profilePictureSize / 2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = R.drawable.cezila),
            contentDescription = stringResource(id = R.string.profile_image),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    brush = GradientBrush,
                    shape = CircleShape
                )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(x = (SpaceSmall + 30.dp) / 2f)
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.h1.copy(fontSize = 24.sp),
                textAlign = TextAlign.Center,
                color = White
            )

            Spacer(modifier = Modifier.width(SpaceSmall))

            IconButton(
                onClick = onMessageClick,
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_mail),
                    contentDescription = stringResource(R.string.message)
                )
            }
        }

        Spacer(modifier = Modifier.height(SpaceMedium))
        if (user.description.isNotBlank()) {
            Text(
                text = user.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = SocialWhite
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
        }

        ProfileStats(
            user = user,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing,
            onEditClick = onEditClick,
            onFollowClick = onFollowClick,
        )
    }

}