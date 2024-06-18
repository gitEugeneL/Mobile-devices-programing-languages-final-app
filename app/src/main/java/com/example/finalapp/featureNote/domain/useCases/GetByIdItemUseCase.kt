package com.example.finalapp.featureNote.domain.useCases

import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository

class GetByIdItemUseCase(private val repository: ItemRepository) {

    suspend operator fun invoke(itemId: Long): Item? {
        return repository.getItemById(itemId)
    }
}