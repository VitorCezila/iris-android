package com.ghn.iris.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.ghn.iris.presentation.ui.theme.LightGray
import com.ghn.iris.presentation.ui.theme.SocialPink
import com.ghn.iris.presentation.ui.theme.Transparent

@Composable
fun UnderlinedTextField(
    text: String = "",
    hint: String = "",
    isError: Boolean = false,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = hint)
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        ),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Transparent,
            focusedIndicatorColor = SocialPink,
            unfocusedIndicatorColor = LightGray,
            disabledIndicatorColor = LightGray
        ),
        singleLine = true
    )
}