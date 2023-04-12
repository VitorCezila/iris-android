package com.ghn.iris.feature_auth.presentation.login_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ghn.iris.R
import com.ghn.iris.core.presentation.ui.theme.*
import com.ghn.iris.core.presentation.components.UnderlinedTextField
import com.ghn.iris.core.presentation.util.Screen
import com.ghn.iris.core.presentation.util.UiEvent
import com.ghn.iris.core.presentation.util.asString
import com.ghn.iris.feature_auth.presentation.util.AuthError
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onLogin: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val state = viewModel.loginState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                is UiEvent.OnLogin -> {
                    onLogin()
                }
                else -> {}
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = 50.dp,
                bottom = 50.dp
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.login_title),
                color = SocialPink,
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                text = stringResource(id = R.string.login_subtitle),
                color = White,
                style = MaterialTheme.typography.h3
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.login_username_email),
                color = White,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            UnderlinedTextField(
                text = emailState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                error = when(emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                keyBoardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(SpaceBigLarge))

            Text(
                text = stringResource(id = R.string.login_password),
                color = White,
                style = MaterialTheme.typography.h2,
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            UnderlinedTextField(
                text = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                keyBoardType = KeyboardType.Password,
                error = when (passwordState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                showPasswordToggle = state.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    viewModel.onEvent(LoginEvent.Login)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = SocialPink),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = SpaceBigLarge, end = SpaceBigLarge)
            ) {
                Text(text = "LOGIN", color = White)
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Dont have an account? ", color = LightGray)
                Box(
                    modifier = Modifier
                        .clickable {
                            onNavigate(
                                Screen.RegisterScreen.route
                            )
                        }
                ) {
                    Text(
                        text = "Sign Up",
                        color = SocialPink,
                    )
                }
            }
        }
    }
}