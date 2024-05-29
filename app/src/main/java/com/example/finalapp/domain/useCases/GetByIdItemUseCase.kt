package com.example.finalapp.domain.useCases

import com.example.finalapp.domain.models.Item
import com.example.finalapp.domain.repositories.ItemRepository

class GetByIdItemUseCase(private val repository: ItemRepository) {

    suspend fun invoke(itemId: Long): Item? {
        return repository.getItemById(itemId)
    }
}