package com.longlegsdev.note.presentation.screen.common

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.longlegsdev.note.presentation.theme.LoadingBarColor
import com.longlegsdev.note.presentation.theme.LoadingBarTrackColor

@Composable
fun LoadingBar(
    modifier: Modifier = Modifier,
    width: Dp = 7.dp,
    size: Dp = 100.dp,
    color: Color = LoadingBarColor,
    trackColor: Color = LoadingBarTrackColor,
) {
    CircularProgressIndicator(
        color = color,
        trackColor = trackColor,
        strokeWidth = width,
        modifier = modifier
            .size(size)
    )
}