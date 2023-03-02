package com.ghn.iris.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ghn.iris.presentation.ui.theme.DarkBlack
import com.ghn.iris.presentation.ui.theme.SpaceBigLarge

@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {},
    showBackArrow: Boolean = false,
    customBackAction: @Composable (() -> Unit)? = null,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable RowScope.() -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(SpaceBigLarge)
            .background(color = DarkBlack),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        val hasContentInLeft = customBackAction != null || showBackArrow

        if (customBackAction != null) {
            IconButton(onClick = { onNavigateUp() }) {
                customBackAction()
            }
        } else if (showBackArrow) {
            IconButton(onClick = { onNavigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        if (hasContentInLeft) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                this@Row.title()
            }
        } else {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                title()
            }
        }
        navActions()
    }
}