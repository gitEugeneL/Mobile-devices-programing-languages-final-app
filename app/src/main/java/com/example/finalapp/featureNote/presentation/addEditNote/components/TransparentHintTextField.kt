package com.example.finalapp.featureNote.presentation.addEditNote.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    singleLine: Boolean = false,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChange(it) },
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = LocalContentColor.current.copy(alpha = ContentAlpha.medium),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}