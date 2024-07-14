package com.longlegsdev.note.presentation.screen.note.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.presentation.theme.FloatingButtonColor

@Composable
fun NoteFloatingButton(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    deleteEnable: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(25.dp)
    ) {
        SmallFloatingActionButton(
            modifier = Modifier
                .size(size),
            onClick = { onClick() },
            containerColor = FloatingButtonColor,
            contentColor = MaterialTheme.colorScheme.onSurface,
            shape = CircleShape
        ) {
            Icon(
                if (deleteEnable) Icons.Filled.Delete
                else Icons.Filled.Add,
                "note floating action button."
            )
        }
    }
}