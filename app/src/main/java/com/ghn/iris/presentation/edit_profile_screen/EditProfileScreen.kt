package com.ghn.iris.presentation.edit_profile_screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.domain.models.Profile
import com.ghn.iris.presentation.components.UnderlinedTextField
import com.ghn.iris.presentation.ui.theme.*
import com.ghn.iris.presentation.util.states.StandardTextFieldState
import com.ghn.iris.util.Constants.ProfilePictureSizeLarge

@Composable
fun EditProfileScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    profile: Profile
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BannerEditSection(
            bannerImage = painterResource(id = R.drawable.sheep),
            profileImage = painterResource(id = R.drawable.cezila),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(SpaceLarge)
        ) {
            Text(
                text = "Name",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = White
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            UnderlinedTextField(
                modifier = Modifier
                    .weight(1f),
                text = viewModel.usernameState.value.text,
                error = viewModel.usernameState.value.error,
                onValueChange = {
                    viewModel.setUsernameState(StandardTextFieldState(text = it))
                },
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(SpaceLarge)
        ) {
            Text(
                text = "Bio",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = White
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            UnderlinedTextField(
                modifier = Modifier
                    .weight(1f),
                text = viewModel.bioState.value.text,
                error = viewModel.bioState.value.error,
                isSingleLine = false,
                onValueChange = {
                    viewModel.setBioState(StandardTextFieldState(text = it))
                },
            )
        }

        Row(
            modifier = Modifier
                .border(width = 0.5.dp, color = Color.Gray, shape = CircleShape)
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { },
            ) {
                Text(text = "Save", color = SocialPink)
            }
            TextButton(
                onClick = { onNavigateUp() },
            ) {
                Text(text = "Discard", color = SocialWhite)
            }
        }

    }
}

@Composable
fun BannerEditSection(
    bannerImage: Painter,
    profileImage: Painter,
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    onBannerClick: () -> Unit = {},
    onProfilePictureClick: () -> Unit = {}
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight + profilePictureSize / 2f)
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
                .clickable { onBannerClick() }
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
                .clickable { onProfilePictureClick() }

        )
    }
}