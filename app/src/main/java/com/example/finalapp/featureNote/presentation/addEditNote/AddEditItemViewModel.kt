package com.example.finalapp.featureNote.presentation.addEditNote

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalapp.R
import com.example.finalapp.featureNote.domain.models.InvalidItemException
import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.useCases.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    application: Application,
    private val itemUseCases: ItemUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _application = application

    private val _itemName = mutableStateOf(
        ItemTextFieldState(
            hint = application.getString(R.string.enter_item_name)
        )
    )
    val itemName: State<ItemTextFieldState> = _itemName

    private val _itemBody = mutableStateOf(
        ItemTextFieldState(
            hint = application.getString(R.string.enter_item_body)
        )
    )
    val itemBody: State<ItemTextFieldState> = _itemBody

    private val _itemColor = mutableStateOf(Item.itemColors.random().toArgb())
    val itemColor: State<Int> = _itemColor

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow

    private var currentItemId: Int? = null

    init {
        savedStateHandle.get<Int>("itemId")?.let { itemId ->
            if (itemId != -1) {
                viewModelScope.launch {
                    itemUseCases.getItemById(itemId)?.also {item ->
                        currentItemId = item.id
                        _itemName.value = itemName.value.copy(
                            text = item.name,
                            isHintVisible = false
                        )
                        _itemBody.value = itemBody.value.copy(
                            text = item.body,
                            isHintVisible = false
                        )
                        _itemColor.value = item.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditItemEvent) {

        when (event) {
            is AddEditItemEvent.ChangedName -> {
                _itemName.value = itemName.value.copy(
                    text = event.value
                )
            }
            is AddEditItemEvent.ChangeNameFocus -> {
                _itemName.value = itemName.value.copy(
                    isHintVisible = !event.focusState.isFocused && itemName.value.text.isBlank()
                )
            }
            is AddEditItemEvent.ChangedBody -> {
                _itemBody.value = itemBody.value.copy(
                    text = event.value
                )
            }
            is AddEditItemEvent.ChangeBodyFocus -> {
                _itemBody.value = itemBody.value.copy(
                    isHintVisible = !event.focusState.isFocused && itemBody.value.text.isBlank()
                )
            }
            is AddEditItemEvent.ChangeColor -> {
                _itemColor.value = event.value
            }
            is AddEditItemEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        itemUseCases.addItem(
                            Item(
                                name = itemName.value.text,
                                body = itemBody.value.text,
                                color = itemColor.value,
                                timestamp = System.currentTimeMillis(),
                                id = currentItemId
                            )
                        )
                        _eventFlow.emit(UIEvent.SaveItem)
                    } catch (e: InvalidItemException) {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                e.message ?: _application.getString(R.string.couldnt_save_item)
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
        object SaveItem : UIEvent()
    }

}