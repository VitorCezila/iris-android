package com.ghn.iris.feature_post.presentation.create_post_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.presentation.components.StandardToolbar
import com.ghn.iris.feature_post.presentation.create_post_screen.components.RowFiles
import com.ghn.iris.core.domain.states.StandardTextFieldState
import com.ghn.iris.core.util.Constants

@Composable
fun CreatePostScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    var showMenu by remember { mutableStateOf(false) }

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
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(Constants.ProfilePictureSize)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = viewModel.content.value.text,
                onValueChange = {
                    viewModel.setContent(StandardTextFieldState(text = it))
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
        
        Spacer(modifier = Modifier.height(SpaceSmall))

        RowFiles(
            showMenu = showMenu,
            onMenuClicked = {
                showMenu = !showMenu
            }
        )
    }
}