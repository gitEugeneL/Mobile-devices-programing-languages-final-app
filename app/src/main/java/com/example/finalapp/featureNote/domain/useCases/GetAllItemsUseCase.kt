package com.example.finalapp.featureNote.domain.useCases

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetAllItemsUseCase(private val repository: ItemRepository) {

    fun invoke(): Flow<List<Item>> {
        return repository.getAllItems()
    }
}