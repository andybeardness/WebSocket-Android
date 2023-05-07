package com.beardness.websocketstest.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.PublicOff
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
import com.beardness.websocketstest.ui.widget.component.SpacerHorizontalComponent
import com.beardness.websocketstest.R

@Composable
fun ConnectionWidget(
    internet: Boolean,
    status: Boolean,
    connectButtonAction: () -> Unit,
    disconnectButtonAction: () -> Unit,

) {
    val connectText = stringResource(id = R.string.connect)
    val disconnectText = stringResource(id = R.string.disconnect)

    val buttonShape = RoundedCornerShape(percent = 50)

    val enableColor = MaterialTheme.colorScheme.primary
    val onEnableColor = MaterialTheme.colorScheme.onPrimary

    val disableColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f)
    val onDisableColor = MaterialTheme.colorScheme.onBackground

    val colorAnimationSpec = tween<Color>(durationMillis = 200)

    val connectButtonColor by animateColorAsState(
        targetValue = when {
            internet && status -> onDisableColor
            internet -> onEnableColor
            else -> onDisableColor
        },
        animationSpec = colorAnimationSpec,
    )

    val connectButtonBackgroundColor by animateColorAsState(
        targetValue = when {
            internet && status -> disableColor
            internet -> enableColor
            else -> disableColor
        },
        animationSpec = colorAnimationSpec,
    )

    val disconnectButtonColor by animateColorAsState(
        targetValue = when {
            internet && status -> onEnableColor
            internet -> onDisableColor
            else -> onDisableColor
        },
        animationSpec = colorAnimationSpec,
    )

    val disconnectButtonBackgroundColor by animateColorAsState(
        targetValue = when {
            internet && status -> enableColor
            internet -> disableColor
            else -> disableColor
        },
        animationSpec = colorAnimationSpec,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 1f)
                .clip(shape = buttonShape)
                .clickable { connectButtonAction() }
                .background(color = connectButtonBackgroundColor)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row {
                Text(
                    text = connectText,
                    color = connectButtonColor,
                )

                Spacer(
                    modifier = Modifier
                        .width(width = 8.dp)
                )

                Icon(
                    imageVector = Icons.Default.Public,
                    contentDescription = null,
                    tint = connectButtonColor,
                )
            }
        }

        SpacerHorizontalComponent()

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(weight = 1f)
                .clip(shape = buttonShape)
                .clickable { disconnectButtonAction() }
                .background(color = disconnectButtonBackgroundColor)
                .padding(horizontal = 24.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row {
                Text(
                    text = disconnectText,
                    color = disconnectButtonColor,
                )

                Spacer(
                    modifier = Modifier
                        .width(width = 8.dp)
                )

                Icon(
                    imageVector = Icons.Default.PublicOff,
                    contentDescription = null,
                    tint = disconnectButtonColor,
                )
            }
        }
    }
}