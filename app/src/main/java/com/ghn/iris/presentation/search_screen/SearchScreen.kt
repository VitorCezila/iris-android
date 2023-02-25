package com.ghn.iris.presentation.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ghn.iris.presentation.ui.theme.White

@Composable
fun SearchScreen(
    navController: NavController,
    ) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "SearchScreen", color = White)
    }
}