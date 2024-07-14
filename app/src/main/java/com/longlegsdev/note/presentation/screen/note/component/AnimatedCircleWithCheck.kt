package com.longlegsdev.note.presentation.screen.note.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCircleWithCheck(
    modifier: Modifier,
    isSelected: Boolean = false,
    width: Dp = 3.dp,
    circleSize: Dp = 50.dp,
    padding: Dp = 20.dp,
    color: Color = Color.Black
) {
    val progress = remember { Animatable(0f) }

    LaunchedEffect(isSelected) {
        if (isSelected) {
            progress.animateTo(1f)
        } else {
            progress.snapTo(0f)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(circleSize)
//            .padding(top = 30.dp, end = 10.dp)
            .padding(padding)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = width.toPx()
            val radius = size.minDimension / 2

            // 원형 테두리
            drawCircle(
                color = color,
                radius = radius,
                style = Stroke(strokeWidth)
            )

            // 체크 표시
            if (isSelected) {
                val path = Path().apply {
                    moveTo(size.width * 0.3f, size.height * 0.55f)
                    lineTo(size.width * 0.45f, size.height * 0.75f)
                    lineTo(size.width * 0.95f, size.height * 0.1f)
                }
                scale(progress.value) {
                    drawPath(
                        path = path,
                        color = color,
                        style = Stroke(strokeWidth, cap = StrokeCap.Round)
                    )
                }
            }
        }
    }
}