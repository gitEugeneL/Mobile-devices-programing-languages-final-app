package com.example.finalapp.featureNote.presentation.notes

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.util.ItemOrder

sealed class ItemsEvent {
    data class Order(val itemOrder: ItemOrder): ItemsEvent()
    data class DeleteItem(val item: Item): ItemsEvent()
    object RestoreItem: ItemsEvent()
    object ToggleOrderSection: ItemsEvent()
}