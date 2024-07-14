package com.longlegsdev.note.presentation.screen.note.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.longlegsdev.note.R
import com.longlegsdev.note.data.entity.NoteEntity
import com.longlegsdev.note.presentation.theme.CHECK_CIRCLE_BOX_COLOR
import com.longlegsdev.note.util.Space

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    note: NoteEntity,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    deleteEnable: Boolean,
    isSelected: Boolean,
    click: () -> Unit,
    longClick: () -> Unit
) {
    Box(
        modifier = modifier
//            .click { click() }
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { click() },
                    onLongPress = { longClick() }
                )
            },
    ) {

        Canvas(
            modifier = Modifier
                .padding(top = 12.dp)
                .matchParentSize()
        ) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        if (deleteEnable) {
            AnimatedCircleWithCheck(
                modifier = Modifier.align(Alignment.TopEnd),
                isSelected = isSelected,
                width = 2.5.dp,
                circleSize = 80.dp,
                padding = 30.dp,
                color = CHECK_CIRCLE_BOX_COLOR
            )
        }

        /* pin image */
        Image(
            modifier = Modifier
                .size(27.dp)
                .align(Alignment.TopCenter),
            painter = painterResource(R.drawable.img_note_pin),
            contentDescription = "note pin marker",
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 20.dp, end = 32.dp)
        ) {
            // title
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Space(height = 8.dp)
            // contents
            Text(
                text = note.text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


