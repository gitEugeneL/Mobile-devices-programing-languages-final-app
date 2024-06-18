package com.example.finalapp.featureNote.presentation.notes

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.util.ItemOrder
import com.example.finalapp.featureNote.domain.util.OrderType

data class ItemState (
    val items: List<Item> = emptyList(),
    val itemOrder: ItemOrder = ItemOrder.Timestamp(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
)