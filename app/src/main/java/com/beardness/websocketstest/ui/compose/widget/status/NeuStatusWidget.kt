package com.beardness.websocketstest.ui.compose.widget.status

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.beardness.websocketstest.R
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuStatusWidget(
    internet: Boolean,
    status: Boolean,
) {
    val connectedText = stringResource(id = R.string.connected)
    val disconnectedText = stringResource(id = R.string.disconnected)
    val noInternetText = stringResource(id = R.string.no_internet)

    val statusText = when {
        internet && status -> connectedText
        internet -> disconnectedText
        else -> noInternetText
    }

    val enableColor = AppTheme.colors.text
    val disableColor = AppTheme.colors.text.copy(alpha = .3f)

    val statusConnectedColor = enableColor.copy(alpha = .3f)
    val statusDisconnectedColor = disableColor.copy(alpha = .3f)

    val colorAnimationSpec = tween<Color>(durationMillis = 200)

    val statusColor by animateColorAsState(
        targetValue = when {
            !internet -> statusDisconnectedColor
            status -> statusConnectedColor
            else -> statusDisconnectedColor
        },
        animationSpec = colorAnimationSpec,
    )

    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = statusText,
        color = statusColor,
    )
}