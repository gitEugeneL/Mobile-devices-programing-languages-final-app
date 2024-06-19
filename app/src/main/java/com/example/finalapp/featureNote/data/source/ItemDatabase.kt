package com.example.finalapp.featureNote.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.featureNote.domain.models.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class ItemDatabase : RoomDatabase() {
    abstract val itemDAO: ItemDAO

    companion object {
        const val DATABASE_NAME = "items_database"
    }

}