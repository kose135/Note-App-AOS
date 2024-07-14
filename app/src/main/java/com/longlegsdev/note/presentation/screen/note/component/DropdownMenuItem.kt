package com.longlegsdev.note.presentation.screen.note.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.R

@Composable
fun DropdownMenuItem(
    isSelected: Boolean = false,
    text: String,
    onClick: () -> Unit
) {
    androidx.compose.material3.DropdownMenuItem(
        modifier = Modifier
            .alpha(
                if (isSelected) 0.5f
                else 1f
            ),
        text = { Text(text = text) },
        onClick = { onClick() },
        trailingIcon = if (isSelected) {
            {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.icon_check),
                    contentDescription = "selected menu item icon"
                )
            }
        } else null
    )
}