package com.ghn.iris.feature_chat.presentation.message_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ghn.iris.core.presentation.ui.theme.White

@Composable
fun MessagesScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Message Screen", color = White)
    }
}