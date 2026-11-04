package com.son.rhym.swipeable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeActionButton(
    modifier: Modifier = Modifier,
    startActionWidth: Dp = 50.dp,
    endActionWidth: Dp = 100.dp,
    onStartActionTriggered: () -> Unit = {},
    startAction: @Composable () -> Unit,
    endAction: @Composable () -> Unit,
    content: @Composable (offsetX: Float) -> Unit
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val startPx = with(density) { startActionWidth.toPx() }
    val endPx = with(density) { endActionWidth.toPx() }
    val offsetX = remember { Animatable(0f) }
    val currentOnStart by rememberUpdatedState(onStartActionTriggered)

    fun settle() {
        scope.launch {
            when {
                offsetX.value > startPx / 2 -> {
                    currentOnStart()
                    offsetX.animateTo(0f, tween(200))
                }
                offsetX.value < -endPx / 2 -> offsetX.animateTo(-endPx, tween(200))
                else -> offsetX.animateTo(0f, tween(200))
            }
        }
    }

    Box(modifier.fillMaxWidth()) {
        // Background actions, one on each side
        Row (
            Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(Modifier.width(startActionWidth).fillMaxHeight()) {
                if (offsetX.value > 0f) startAction()
            }
            Box(Modifier.width(endActionWidth).fillMaxHeight()) {
                if (offsetX.value < 0f) endAction()
            }
        }

        // Foreground content (must be opaque to hide the actions)
        Box(
            Modifier
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .pointerInput(startPx, endPx) {
                    detectHorizontalDragGestures(
                        onDragEnd = { settle() },
                        onDragCancel = { settle() },
                        onHorizontalDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                offsetX.snapTo(
                                    (offsetX.value + dragAmount).coerceIn(-endPx, startPx)
                                )
                            }
                        }
                    )
                }
        ) { content(offsetX.value) }
    }
}