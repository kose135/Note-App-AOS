package com.longlegsdev.note.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController


// Disable Vertical Scroll
val VerticalScrollConsumer = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) =
        available.copy(x = 0f)
}

fun Modifier.Companion.disableVerticalScroll() =
    this.nestedScroll(VerticalScrollConsumer)

// Disable Horizontal Scroll
val HorizontalScrollConsumer = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource) =
        available.copy(y = 0f)
}

fun Modifier.Companion.disableHorizontalScroll() =
    this.nestedScroll(HorizontalScrollConsumer)


@Composable
fun Space(height: Dp = 0.dp, width: Dp = 0.dp) = Spacer(
    Modifier
        .height(height)
        .width(width)
)

val NavController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavController.back() {
    if (this.canGoBack) {
        this.popBackStack()
    }
}

/**
 * ripple effect가 없는 click 함수
 */
inline fun Modifier.click(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}