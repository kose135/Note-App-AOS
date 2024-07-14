package com.longlegsdev.note.presentation.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.longlegsdev.note.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    duration: Long = 2500L,
    todo: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_note))
    val progress by animateLottieCompositionAsState(
        composition = composition,
    )

    LaunchedEffect(key1 = true) {
        delay(duration)
        todo()
    }

    SplashLogo(
        composition = composition,
        progress = progress
    )
}

@Composable
fun SplashLogo(
    composition: LottieComposition?,
    progress: Float
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier
                .background(Color.Transparent)
                .size(200.dp),
            composition = composition,
            progress = { progress },
        )
    }
}
