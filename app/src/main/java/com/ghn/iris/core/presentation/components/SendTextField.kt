package com.ghn.iris.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ghn.iris.R
import com.ghn.iris.core.domain.states.StandardTextFieldState

@Composable
fun SendTextField(
    modifier: Modifier = Modifier,
    state: StandardTextFieldState,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    hint: String = "",
    canSendMessage: Boolean = true,
    isLoading: Boolean = false,
    focusRequester: FocusRequester = FocusRequester()
) {
    Row(
        modifier = modifier
            .height(50.dp)
            .background(color = Color.DarkGray, RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StandardTextField(
            text = state.text,
            onValueChange = onValueChange,
            hint = hint,
            focusRequester = focusRequester,
            backgroundColor = Color.Transparent,
            modifier = Modifier
                .fillMaxHeight(),
            trealingIcon = Icons.Default.Send,
            onSend = onSend
        )
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        } else {
            IconButton(
                onClick = onSend,
                enabled = state.error == null || !canSendMessage
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    tint = if (state.error == null && canSendMessage) {
                        MaterialTheme.colors.primary
                    } else MaterialTheme.colors.background,
                    contentDescription = stringResource(id = R.string.send_comment)
                )
            }
        }
    }

}