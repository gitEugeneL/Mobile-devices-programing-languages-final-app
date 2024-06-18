package com.example.finalapp.featureNote.presentation.items

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finalapp.featureNote.domain.useCases.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.util.ItemOrder
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemUseCases: ItemUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ItemState())
    val state: State<ItemState> = _state

    private var recentlyDeletedItem: Item? = null

    private var getItemJob: Job? = null

    init {
        getItems(state.value.itemOrder)
    }

    fun onEvent(event: ItemsEvent) {
        when (event) {
            is ItemsEvent.DeleteItem -> {
                viewModelScope.launch {
                    itemUseCases.deleteItem(event.item)
                    recentlyDeletedItem = event.item
                }
            }

            is ItemsEvent.Order -> {
                if (
                    state.value.itemOrder::class == event.itemOrder::class
                    && state.value.itemOrder.orderType == event.itemOrder.orderType
                ) {
                    return
                }
                getItems(event.itemOrder)
            }

            is ItemsEvent.RestoreItem -> {
                viewModelScope.launch {
                    itemUseCases.addItem(recentlyDeletedItem ?: return@launch)
                    recentlyDeletedItem = null
                }
            }

            is ItemsEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !_state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getItems(itemOrder: ItemOrder) {
        getItemJob?.cancel()
        getItemJob = itemUseCases.getAllItems(itemOrder)
            .onEach { notes ->
                _state.value = _state.value.copy(
                    items = notes,
                    itemOrder = itemOrder
                )
            }.launchIn(viewModelScope)

    }
}
