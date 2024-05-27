package com.example.finalapp.domain.repositories

import com.example.finalapp.domain.models.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getAllItems(): Flow<List<Item>>

    suspend fun getItemById(itemId: Long) : Item?

    suspend fun createItem(item: Item)

    suspend fun deleteItem(item: Item)
}