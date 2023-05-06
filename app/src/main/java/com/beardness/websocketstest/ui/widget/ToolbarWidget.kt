package com.beardness.websocketstest.ui.widget

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.widget.component.LogoComponent
import com.beardness.websocketstest.ui.widget.component.SpacerHorizontalComponent
import com.beardness.websocketstest.ui.widget.component.SpacerVerticalComponent
import com.beardness.websocketstest.ui.widget.component.UrlInputComponent

private const val CLOSED_DEGREES = 0f
private const val OPENED_DEGREES = 180f

@Composable
fun ToolbarWidget(
    internet: Boolean,
    url: String,
    onUrlChanged: (new: String) -> Unit,
    open: Boolean,
    onClickOpen: () -> Unit,
) {
    val animationDuration = 250

    val urlEnterAnimation = fadeIn(animationSpec = tween(durationMillis = animationDuration)) +
            slideInHorizontally(animationSpec = tween(durationMillis = animationDuration))

    val urlExitAnimation = fadeOut(animationSpec = tween(durationMillis = animationDuration)) +
            slideOutHorizontally(animationSpec = tween(durationMillis = animationDuration))

    val degrees by animateFloatAsState(
        targetValue = when {
            open -> OPENED_DEGREES
            else -> CLOSED_DEGREES
        },
        animationSpec = tween(durationMillis = 200),
    )

    val toolbarColor = MaterialTheme.colorScheme.onBackground
    val toolbarBackgroundColor = MaterialTheme.colorScheme.background

    val shape = RoundedCornerShape(percent = 50)

    Column(
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 64.dp)
                .background(color = toolbarBackgroundColor)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LogoComponent(modifier = Modifier.weight(weight = 1f))

            SpacerHorizontalComponent()

            Box(
                modifier = Modifier
                    .size(size = 64.dp)
                    .clip(shape = shape)
                    .clickable { onClickOpen() }
                    .rotate(degrees = degrees),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(size = 32.dp),
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = toolbarColor,
                )
            }
        }

        AnimatedVisibility(
            visible = open,
            enter = urlEnterAnimation,
            exit = urlExitAnimation,
        ) {
            SpacerVerticalComponent()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 64.dp)
                    .background(color = toolbarBackgroundColor)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                UrlInputComponent(
                    modifier = Modifier,
                    internet = internet,
                    url = url,
                    onUrlChanged = onUrlChanged,
                )
            }
        }
    }
}