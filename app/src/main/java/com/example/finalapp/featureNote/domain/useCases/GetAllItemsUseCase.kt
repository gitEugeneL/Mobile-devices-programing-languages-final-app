package com.example.finalapp.featureNote.domain.useCases

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository
import com.example.finalapp.featureNote.domain.util.ItemOrder
import com.example.finalapp.featureNote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllItemsUseCase(private val repository: ItemRepository) {

    operator fun invoke(
        itemOrder: ItemOrder = ItemOrder.Timestamp(OrderType.Descending)
    ): Flow<List<Item>> {
        return repository.getAllItems().map { items ->
            when (itemOrder.orderType) {
                is OrderType.Ascending -> {
                    when (itemOrder) {
                        is ItemOrder.Name -> items.sortedBy { it.name.lowercase() }
                        is ItemOrder.Body -> items.sortedBy { it.body.lowercase() }
                        is ItemOrder.Timestamp -> items.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when (itemOrder) {
                        is ItemOrder.Name -> items.sortedByDescending { it.name.lowercase() }
                        is ItemOrder.Body -> items.sortedByDescending { it.body.lowercase() }
                        is ItemOrder.Timestamp -> items.sortedByDescending { it.timestamp }
                    }
                }
            }

        }
    }
}