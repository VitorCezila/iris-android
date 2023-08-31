package com.ghn.iris.feature_profile.presentation.edit_profile_screen

import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ghn.iris.R
import com.ghn.iris.core.domain.models.Profile
import com.ghn.iris.core.domain.states.StandardTextFieldState
import com.ghn.iris.core.presentation.components.UnderlinedTextField
import com.ghn.iris.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.ghn.iris.core.presentation.ui.theme.SocialPink
import com.ghn.iris.core.presentation.ui.theme.SocialWhite
import com.ghn.iris.core.presentation.ui.theme.SpaceLarge
import com.ghn.iris.core.presentation.ui.theme.SpaceSmall
import com.ghn.iris.core.presentation.ui.theme.White
import com.ghn.iris.core.presentation.util.CropActivityResultContract
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.presentation.util.asString
import com.ghn.iris.core.presentation.util.loadBitmapFromUri
import com.ghn.iris.core.util.base64ToImageBitmap
import com.ghn.iris.feature_profile.presentation.util.EditProfileError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditProfileScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: EditProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureSizeLarge,
) {
    val profileState = viewModel.profileState.value

    val cropProfilePictureLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract()
    ) {
        viewModel.onEvent(EditProfileEvent.CropProfilePicture(it))
    }
    val cropBannerImageLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract()
    ) {
        viewModel.onEvent(EditProfileEvent.CropBannerImage(it))
    }
    val profilePictureGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (it == null) {
            return@rememberLauncherForActivityResult
        }
        cropProfilePictureLauncher.launch(it)
    }
    val bannerImageGalleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (it == null) {
            return@rememberLauncherForActivityResult
        }
        cropBannerImageLauncher.launch(it)
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }

                is UiEvent.NavigateUp -> {
                    onNavigateUp()
                }

                else -> {}
            }
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BannerEditSection(
            bannerImage = viewModel.bannerUri.value?.let { context.loadBitmapFromUri(it) }
                ?: profileState.profile?.profileBannerBase64?.base64ToImageBitmap(),
            profileImage = viewModel.profilePictureUri.value?.let { context.loadBitmapFromUri(it) }
                ?: profileState.profile?.profileImageBase64?.base64ToImageBitmap(),
            profilePictureSize = profilePictureSize,
            onBannerClick = {
                bannerImageGalleryLauncher.launch("image/*")
            },
            onProfilePictureClick = {
                profilePictureGalleryLauncher.launch("image/*")
            }
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
                error = when (viewModel.usernameState.value.error) {
                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                onValueChange = {
                    viewModel.onEvent(
                        EditProfileEvent.EnteredUsername(it)
                    )
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
                error = when (viewModel.bioState.value.error) {
                    is EditProfileError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                isSingleLine = false,
                onValueChange = {
                    viewModel.onEvent(
                        EditProfileEvent.EnteredBio(it)
                    )
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
                onClick = {
                    viewModel.onEvent(EditProfileEvent.UpdateProfile)
                },
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
    bannerImage: Bitmap?,
    profileImage: Bitmap?,
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
        if (bannerImage != null) {
            Image(
                bitmap = bannerImage.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(bannerHeight)
                    .clickable { onBannerClick() }
            )
        }

        if (profileImage != null) {
            Image(
                bitmap = profileImage.asImageBitmap(),
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
}