package com.example.finalapp.featureNote.presentation.items.components

import androidx.compose.material.Icon
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.finalapp.featureNote.domain.models.Item
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.graphics.ColorUtils
import com.example.finalapp.R


@Composable
fun ItemElement(
    item: Item,
    modifier: Modifier = Modifier,
    borderRadius: Dp = 8.dp,
    curBorderSize: Dp = 24.dp,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - curBorderSize.toPx(), 0f)
                lineTo(size.width, curBorderSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                lineTo(0f, 0f)
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(item.color),
                    size = size,
                    cornerRadius = CornerRadius(borderRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(item.color, Color.Black.hashCode(), 0.15f)
                    ),
                    topLeft = Offset(size.width - curBorderSize.toPx(), -120f),
                    size = Size(
                        curBorderSize.toPx() + 120f, curBorderSize.toPx() + 120f
                    ),
                    cornerRadius = CornerRadius(borderRadius.toPx())
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.body,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                maxLines = 12,
                overflow = TextOverflow.Ellipsis,
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_item),
            )
        }
    }
}