package com.example.finalapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalapp.domain.models.Item

@Database(entities = [Item::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val itemDAO: ItemDAO
}