package com.beardness.websocketstest.ui.compose.component.surface

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOutQuint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

private const val ANIMATION_DURATION = 10
private const val SHADOWS_ALPHA = .15f
private val EASING = EaseOutQuint

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NeuTapSurface(
    modifier: Modifier,
    color: Color,
    corner: Dp,
    elevation: Dp,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    var tap by remember { mutableStateOf(value = false) }
    var moved by remember { mutableStateOf(value = false) }

    val tintColor by animateColorAsState(
        targetValue = if (tap) Color.Black.copy(alpha = .06f) else Color.Transparent,
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = EASING,
        )
    )

    val actionDown: () -> Unit = {
        tap = true
    }

    val actionMove: () -> Unit = {
        if (!moved) {
            moved = true
            tap = false
        }
    }

    val actionUp: () -> Unit = {
        if (!moved) {
            tap = false
            onClick()
        }

        moved = false
    }

    Box(
        modifier = modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .width(intrinsicSize = IntrinsicSize.Min)
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        actionDown()
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        actionUp()
                        true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        actionMove()
                        true
                    }
                    else -> false
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = tap,
            enter = fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = EASING)),
            exit = fadeOut(animationSpec = tween(durationMillis = 0, easing = EASING)),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .neu(
                        lightShadowColor = Color.White.copy(alpha = SHADOWS_ALPHA),
                        darkShadowColor = Color.Black.copy(alpha = SHADOWS_ALPHA),
                        shadowElevation = elevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Pressed(RoundedCorner(radius = corner)),
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(size = corner),
                colors = CardDefaults.cardColors(
                    containerColor = color,
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = tintColor),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }
            }
        }

        AnimatedVisibility(
            visible = !tap,
            enter = fadeIn(animationSpec = tween(durationMillis = ANIMATION_DURATION, easing = EASING)),
            exit = fadeOut(animationSpec = tween(durationMillis = 0, easing = EASING)),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .neu(
                        lightShadowColor = Color.White.copy(alpha = SHADOWS_ALPHA),
                        darkShadowColor = Color.Black.copy(alpha = SHADOWS_ALPHA),
                        shadowElevation = elevation,
                        lightSource = LightSource.LEFT_TOP,
                        shape = Flat(RoundedCorner(radius = corner)),
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                shape = RoundedCornerShape(size = corner),
                colors = CardDefaults.cardColors(
                    containerColor = color,
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = tintColor),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }
            }
        }
    }
}

fun Modifier.clickableClean(
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    onClick: () -> Unit,
) = composed(
    factory = {
        this.then(
            Modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onClick() }
            )
        )
    }
)