package com.example.finalapp.featureNote.domain.util

sealed class ItemOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): ItemOrder(orderType)
    class Body(orderType: OrderType): ItemOrder(orderType)
    class Timestamp(orderType: OrderType): ItemOrder(orderType)

    fun copy(orderType: OrderType): ItemOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Body -> Body(orderType)
            is Timestamp -> Timestamp(orderType)
        }
    }

}