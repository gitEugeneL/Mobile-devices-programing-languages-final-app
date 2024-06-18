package com.example.finalapp.featureNote.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}