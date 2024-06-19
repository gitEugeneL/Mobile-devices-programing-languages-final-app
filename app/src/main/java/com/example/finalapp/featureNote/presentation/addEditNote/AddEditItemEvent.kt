package com.example.finalapp.featureNote.presentation.addEditNote

import androidx.compose.ui.focus.FocusState

sealed class AddEditItemEvent {
    data class ChangedName(val value: String): AddEditItemEvent()
    data class ChangeNameFocus(val focusState: FocusState): AddEditItemEvent()
    data class ChangedBody(val value: String): AddEditItemEvent()
    data class ChangeBodyFocus(val focusState: FocusState): AddEditItemEvent()
    data class ChangeColor(val value: Int): AddEditItemEvent()
    object SaveNote: AddEditItemEvent()
}