package com.example.finalapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalapp.ui.theme.Orange
import com.example.finalapp.ui.theme.OrangeStrong
import com.example.finalapp.ui.theme.Red
import com.example.finalapp.ui.theme.Yellow

@Entity
data class Item(
    @PrimaryKey
    val id: Long? = null,
    val name: String,
    val body: String,
    val timestamp: Long,
    val color: Int
) {
    companion object {
        val itemColors = listOf(Red, Orange, OrangeStrong, Yellow)
    }
}