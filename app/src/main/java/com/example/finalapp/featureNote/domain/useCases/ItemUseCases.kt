package com.example.finalapp.featureNote.domain.useCases

data class ItemUseCases (
    val getAllItems: GetAllItemsUseCase,
    val deleteItem: DeleteItemUseCase,
    val addItem: AddItemUseCase,
)