package com.example.finalapp.featureNote.domain.useCases

import com.example.finalapp.featureNote.domain.models.InvalidItemException
import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository

class AddItemUseCase(
    private val repository: ItemRepository
) {

    @Throws(InvalidItemException::class)
    suspend operator fun invoke(item: Item) {
        if (item.name.isBlank()) {
            throw InvalidItemException("Item name can't be empty")
        }
        if (item.name.length < 3) {
            throw InvalidItemException("Item name can't be less than 3 characters")
        }
        if (item.body.isBlank()) {
            throw InvalidItemException("Item body can't be empty")
        }
        if (item.body.length < 3) {
            throw InvalidItemException("Item body can't be less than 3 characters")
        }
        repository.putItem(item)
    }

}