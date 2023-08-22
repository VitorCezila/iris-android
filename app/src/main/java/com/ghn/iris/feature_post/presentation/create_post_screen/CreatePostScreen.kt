package com.ghn.iris.feature_post.presentation.create_post_screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.presentation.util.CropActivityResultContract
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.presentation.util.asString
import com.ghn.iris.core.presentation.util.loadBitmapFromUri
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun CreatePostScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    var showMenu by remember { mutableStateOf(false) }
    val imageUri = viewModel.chosenImageUri.value

    val cropActivityLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract()
    ) {
        viewModel.onEvent(CreatePostEvent.CropImage(it))
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (it != null) {
            cropActivityLauncher.launch(it)
        }
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
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = stringResource(id = R.string.create),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body2,
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            showBackArrow = true,
            customBackAction = {
                Text(text = "Discard", color = SocialBlue)
            },
            navActions = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(CreatePostEvent.CreatePost)
                    },
                ) {
                    Text(text = "Save", color = SocialPink)
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceMedium)
        ) {
            Image(
                painterResource(id = R.drawable.sheep),
                contentDescription = stringResource(id = R.string.profile_image),
                modifier = Modifier
                    .size(ProfilePictureSize)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = viewModel.contentState.value.text,
                onValueChange = {
                    viewModel.onEvent(
                        CreatePostEvent.EnterContent(it)
                    )
                },
                placeholder = {
                    Text(text = "What's on your mind?")
                },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Transparent,
                    textColor = White,
                    cursorColor = White,
                    focusedBorderColor = Transparent,
                    unfocusedBorderColor = Transparent
                )
            )
        }
        
        imageUri?.let { uri ->
            val imageBitMap = context.loadBitmapFromUri(uri)
            if (imageBitMap != null) {
                Image(
                    bitmap = imageBitMap,
                    contentDescription = stringResource(id = R.string.post_image),
                    modifier = Modifier
                        .padding(SpaceLarge)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(SpaceSmall))

        RowFiles(
            showMenu = showMenu,
            onMenuClicked = {
                showMenu = !showMenu
            },
            onGalleryIsClicked = {
                galleryLauncher.launch("image/*")
            },
            onCameraIsClicked = {
                GlobalScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "This feature is coming soon"
                    )
                }

            },
            onGifIsClicked = {
                GlobalScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "This feature is coming soon"
                    )
                }
            }
        )
    }
}