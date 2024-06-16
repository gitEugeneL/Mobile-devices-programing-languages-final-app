package com.example.finalapp.featureNote.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalapp.featureNote.domain.models.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO {

    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE id = :itemId")
    suspend fun getItemById(itemId: Long): Item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putItem(item: Item)

    @Delete
    suspend fun deleteItem(item: Item)
}