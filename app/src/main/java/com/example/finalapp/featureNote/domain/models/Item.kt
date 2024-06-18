package com.example.finalapp.featureNote.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalapp.ui.theme.*

@Entity
data class Item(
    @PrimaryKey val id: Long? = null,
    val name: String,
    val body: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val itemColors = listOf(Red, Orange, OrangeStrong, Yellow)
    }
}

class InvalidItemException(message: String): Exception(message)