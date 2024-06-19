package com.example.finalapp.common.domain.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {

    data class StringValue(val value: String): UIText()
    class StringResource(
        @StringRes val resourceId: Int,
        vararg val args: Any
    ): UIText()

    @Composable
    fun asString(): String {
        return when(this) {
            is StringValue -> value
            is StringResource -> stringResource(resourceId, *args)
        }
    }

}