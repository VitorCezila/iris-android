package com.ghn.iris.feature_profile.presentation.search_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.domain.models.UserItem
import com.ghn.iris.core.presentation.components.UserProfileItem
import com.ghn.iris.core.util.Screen

@Composable
fun SearchScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val state = viewModel.searchState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge)
    ) {
        OutlinedTextField(
            value = viewModel.searchFieldState.value.text,
            onValueChange = {
                viewModel.onEvent(SearchEvent.Query(it))
            },
            isError = viewModel.searchFieldState.value.error != null,
            placeholder = {
                Text(text = stringResource(R.string.search_for_people))
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = DarkGray,
                textColor = White,
                cursorColor = SocialPink,
                focusedBorderColor = DarkGray,
                unfocusedBorderColor = DarkGray
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon),
                    tint = SocialBlue
                )
            },
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(55.dp)
                .fillMaxWidth()
        )
        if (viewModel.searchFieldState.value.error != null) {
            Text(
                text = "",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(SpaceMedium))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.userItems.size) { i ->
                val user = state.userItems[i]
                UserProfileItem(
                    user = user,
                    actionIcon = {
                        IconButton(
                            onClick = {
                                viewModel.onEvent(SearchEvent.ToggleFollowState(user.userId))
                            },
                            modifier = Modifier
                                .size(IconSizeMedium)
                        ) {
                            Icon(
                                imageVector = if (user.isFollowing) {
                                    Icons.Default.PersonRemove
                                } else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground,
                            )
                        }
                    },
                    onUserClick = {
                        onNavigate(
                            Screen.ProfileScreen.route + "?userId=${user.userId}"
                        )
                    }
                )
            }
        }
    }
}
