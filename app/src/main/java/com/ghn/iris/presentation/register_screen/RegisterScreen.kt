package com.ghn.iris.presentation.register_screen

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

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
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
        Text(
            text = stringResource(id = R.string.register_title),
            color = SocialPink,
            style = MaterialTheme.typography.h1
        )

        Spacer(modifier = Modifier.height(SpaceBigLarge))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = R.string.register_create_email),
                color = White,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            UnderlinedTextField(
                text = viewModel.emailText.value,
                onValueChange = {
                    viewModel.setEmailText(it)
                },
                keyBoardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(SpaceBigLarge))

            Text(
                text = stringResource(id = R.string.register_create_username),
                color = White,
                style = MaterialTheme.typography.h2,
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            UnderlinedTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                keyBoardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(SpaceBigLarge))

            Text(
                text = stringResource(id = R.string.register_create_password),
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
                Text(text = "SIGN UP", color = White)
            }
        }
    }
}