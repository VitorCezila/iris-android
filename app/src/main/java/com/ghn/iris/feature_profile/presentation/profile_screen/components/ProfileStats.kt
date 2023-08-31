package com.ghn.iris.feature_profile.presentation.profile_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.domain.models.User

@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    isFollowing: Boolean = true,
    onFollowClick: () -> Unit = {},
    onEditClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileNumber(
            number = user.followerCount,
            text = stringResource(id = R.string.followers)
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        ProfileNumber(
            number = user.followingCount,
            text = stringResource(id = R.string.following)
        )

        Spacer(modifier = Modifier.width(SpaceLarge))

        if (isOwnProfile) {
            Button(
                onClick = onEditClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = DarkBlack),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = LightGray,
                        shape = RoundedCornerShape(50)
                    )
            ) {
                Text(
                    text = stringResource(R.string.edit_profile),
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Button(
                onClick = onFollowClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing) {
                        DarkBlack
                    } else SocialPink
                ),
                shape = RoundedCornerShape(50),
                modifier =
                if (isFollowing) {
                    Modifier
                        .weight(1f)
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(50)
                        )
                } else Modifier.weight(1f)
            ) {
                Text(
                    text = if (isFollowing) {
                        stringResource(id = R.string.unfollow)
                    } else stringResource(id = R.string.follow),
                    color = White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}