package com.example.finalapp.featureNote.domain.repositories

import com.example.finalapp.featureNote.domain.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItems(): Flow<List<Item>>

    suspend fun getItemById(itemId: Long) : Item?

    suspend fun putItem(item: Item)

    suspend fun deleteItem(item: Item)
}