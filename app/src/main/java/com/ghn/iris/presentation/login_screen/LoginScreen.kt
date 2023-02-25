package com.ghn.iris.presentation.login_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ghn.iris.R
import com.ghn.iris.presentation.components.UnderlinedTextField
import com.ghn.iris.presentation.ui.theme.*
import com.ghn.iris.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
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
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
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
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
                },
                keyBoardType = KeyboardType.Password,
                showPasswordToggle = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
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
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = SocialPink),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = SpaceBigLarge, end = SpaceBigLarge)
            ) {
                Text(text = "LOGIN", color = White)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Dont have an account? ", color = LightGray)
                Box(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.RegisterScreen.route)
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