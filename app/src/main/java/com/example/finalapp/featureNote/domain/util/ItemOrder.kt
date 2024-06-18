package com.example.finalapp.featureNote.domain.util

sealed class ItemOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): ItemOrder(orderType)
    class Body(orderType: OrderType): ItemOrder(orderType)
    class Timestamp(orderType: OrderType): ItemOrder(orderType)
}