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
import com.ghn.iris.core.domain.states.StandardTextFieldState

@Composable
fun SearchScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge)
    ) {
        OutlinedTextField(
            value = viewModel.searchState.value.text,
            onValueChange = {
                viewModel.setSearchState(
                    StandardTextFieldState(text = it)
                )
            },
            isError = viewModel.searchState.value.error != null,
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
        if (viewModel.searchState.value.error != null) {
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
            items(5) {
                UserProfileItem(
                    user = mockUser,
                    actionIcon = {
                        IconButton(
                            onClick = {
                            },
                            modifier = Modifier
                                .size(IconSizeMedium)
                        ) {
                            Icon(
                                imageVector = if (mockUser.isFollowing) {
                                    Icons.Default.PersonRemove
                                } else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground,
                            )
                        }
                    }
                )
            }
        }
    }
}

private val mockUser = UserItem(
    userId = "teste",
    username = "cezila",
    profilePictureUrl = "",
    bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse a sem quam. Integer placerat efficitur mattis. Ut magna nunc, dictum rutrum augue ut, condimentum sollicitudin nisl. In est turpis, egestas in ex eu.",
    isFollowing = true
)