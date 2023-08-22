package com.ghn.iris.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.ghn.iris.core.presentation.ui.theme.IconSizeMedium
import com.ghn.iris.core.presentation.ui.theme.SocialPink

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 400,
    error: String = "",
    style: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    backgroundColor: Color = MaterialTheme.colors.surface,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    trealingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = FocusRequester(),
    onSend:() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(1f)
    ) {
        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            maxLines = maxLines,
            textStyle = style,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,

            ),
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            },
            isError = error != "",
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            visualTransformation = VisualTransformation.None,
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
                icon
            } else null,
            trailingIcon = if (trealingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = trealingIcon,
                        contentDescription = null,
                        tint = SocialPink,
                        modifier = Modifier
                            .size(IconSizeMedium)
                            .clickable {
                                onSend()
                            }
                    )
                }
                icon
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
        )
        if (error.isNotEmpty()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}