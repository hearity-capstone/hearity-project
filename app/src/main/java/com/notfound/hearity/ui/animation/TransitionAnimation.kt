package com.notfound.hearity.ui.animation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun scaleFadeEnterTransition(): EnterTransition {
    return scaleIn(
        initialScale = 0.75f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    ) + fadeIn(
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
}

fun scaleFadeExitTransition(): ExitTransition {
    return scaleOut(
        targetScale = 0.75f,
        animationSpec = tween(500, easing = FastOutSlowInEasing)
    ) + fadeOut(animationSpec = tween(durationMillis = 500))
}

fun slideFromRightEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
}

fun slideToRightExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
}

fun defaultFadeEnterTransition(): EnterTransition = fadeIn(animationSpec = tween(300))
fun defaultFadeExitTransition(): ExitTransition = fadeOut(animationSpec = tween(300))
