package com.example.finalapp.domain.useCases

import com.example.finalapp.domain.models.Item
import com.example.finalapp.domain.repositories.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase(private val repository: ItemRepository) {

    fun invoke(): Flow<List<Item>> {
        return repository.getAllItems()
    }
}