package com.longlegsdev.note.presentation.screen.note.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.R

data class DrawerMenuItemData(
    val id: String,
    val title: String,
    val contentDescription: String?,
    val icon: ImageVector
) {
    companion object {
        val drawerMenuItems = listOf(
            DrawerMenuItemData(
                id = "sync",
                title = "동기화",
                contentDescription = "",
                icon = Icons.Default.Share
            ),
            DrawerMenuItemData(
                id = "trash",
                title = "휴지통",
                contentDescription = "",
                icon = Icons.Default.Delete
            ),
        )
    }
}