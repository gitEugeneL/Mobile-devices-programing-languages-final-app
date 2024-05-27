package com.example.finalapp.data.repositories

import com.example.finalapp.data.source.ItemDAO
import com.example.finalapp.domain.models.Item
import com.example.finalapp.domain.repositories.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImp(private val context: ItemDAO) : ItemRepository {

    override fun getAllItems(): Flow<List<Item>> {
        return context.getAllItems()
    }

    override suspend fun getItemById(itemId: Long) : Item? {
        return context.getItemById(itemId)
    }

    override suspend fun createItem(item: Item) {
        context.createItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        context.deleteItem(item)
    }
}