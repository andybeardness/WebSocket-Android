package com.beardness.websocketstest.ui.widget.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.R

@Composable
fun SendButtonComponent(
    internet: Boolean,
    status: Boolean,
    action: () -> Unit,
) {
    val sendText = stringResource(id = R.string.send)

    val buttonShape = RoundedCornerShape(percent = 50)

    val enableColor = MaterialTheme.colorScheme.primary
    val onEnableColor = MaterialTheme.colorScheme.onPrimary

    val disableColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f)
    val onDisableColor = MaterialTheme.colorScheme.onBackground

    val colorAnimationSpec = tween<Color>(durationMillis = 200)

    val sendButtonColor by animateColorAsState(
        targetValue = when {
            !internet -> onDisableColor
            status -> onEnableColor
            else -> onDisableColor
        },
        animationSpec = colorAnimationSpec,
    )

    val sendButtonBackgroundColor by animateColorAsState(
        targetValue = when {
            !internet -> disableColor
            status -> enableColor
            else -> disableColor
        },
        animationSpec = colorAnimationSpec,
    )

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = buttonShape)
            .clickable { action() }
            .background(color = sendButtonBackgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row {
            Text(
                text = sendText,
                color = sendButtonColor,
            )

            Spacer(
                modifier = Modifier
                    .width(width = 8.dp),
            )

            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                tint = sendButtonColor,
            )
        }
    }
}