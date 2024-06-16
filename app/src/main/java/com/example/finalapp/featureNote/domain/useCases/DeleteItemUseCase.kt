package com.example.finalapp.featureNote.domain.useCases

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository

class DeleteItemUseCase(private val repository: ItemRepository) {

    suspend fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}