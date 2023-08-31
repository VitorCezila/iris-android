package com.ghn.iris.feature_profile.presentation.profile_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.ghn.iris.core.presentation.ui.theme.LightGray
import com.ghn.iris.core.presentation.ui.theme.White

@Composable
fun ProfileNumber(
    number: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.body2,
            color = White,
            textAlign = TextAlign.Center
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = LightGray,
            textAlign = TextAlign.Center
        )
    }
}