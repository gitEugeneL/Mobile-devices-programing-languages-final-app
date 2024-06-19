package com.example.finalapp.featureNote.presentation.items.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.finalapp.R
import com.example.finalapp.featureNote.domain.util.ItemOrder
import com.example.finalapp.featureNote.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    itemOrder: ItemOrder,
    onOrderChange: (ItemOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.item_name),
                selected = itemOrder is ItemOrder.Name,
                onSelect = { onOrderChange(ItemOrder.Name(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.item_body),
                selected = itemOrder is ItemOrder.Body,
                onSelect = { onOrderChange(ItemOrder.Body(itemOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.item_date),
                selected = itemOrder is ItemOrder.Timestamp,
                onSelect = { onOrderChange(ItemOrder.Timestamp(itemOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.order_ascending),
                selected = itemOrder.orderType is OrderType.Ascending,
                onSelect = { onOrderChange(itemOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.order_descending),
                selected = itemOrder.orderType is OrderType.Descending,
                onSelect = { onOrderChange(itemOrder.copy(OrderType.Descending)) }
            )
        }
    }
}