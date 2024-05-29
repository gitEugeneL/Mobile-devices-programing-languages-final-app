package com.example.finalapp.domain.useCases

import com.example.finalapp.domain.models.Item
import com.example.finalapp.domain.repositories.ItemRepository

class DeleteItemUseCase(private val repository: ItemRepository) {

    suspend fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}