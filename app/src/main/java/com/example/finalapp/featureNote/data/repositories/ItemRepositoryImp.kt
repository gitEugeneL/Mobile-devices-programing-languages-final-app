package com.example.finalapp.featureNote.data.repositories

import com.example.finalapp.featureNote.data.source.ItemDAO
import com.example.finalapp.featureNote.domain.models.Item
import com.example.finalapp.featureNote.domain.repositories.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImp(private val context: ItemDAO) : ItemRepository {

    override fun getAllItems(): Flow<List<Item>> {
        return context.getAllItems()
    }

    override suspend fun getItemById(itemId: Int) : Item? {
        return context.getItemById(itemId)
    }

    override suspend fun putItem(item: Item) {
        context.putItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        context.deleteItem(item)
    }
}