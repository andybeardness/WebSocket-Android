package com.beardness.websocketstest.ui.compose.widget.input

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.R
import com.beardness.websocketstest.ui.compose.component.surface.NeuPressedSurface
import com.beardness.websocketstest.ui.compose.component.surface.clickableClean
import com.beardness.websocketstest.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NeuTextInput(
    modifier: Modifier,
    verticalPadding: Dp,
    internet: Boolean,
    status: Boolean,
    input: String,
    onInputChange: (new: String) -> Unit,
    sendButtonAction: () -> Unit,
) {
    val enterMessageText = stringResource(id = R.string.enter_message)
    val noConnectText = stringResource(id = R.string.no_connect)
    val noInternetText = stringResource(id = R.string.no_internet)

    val enabled = internet && status

    val placeholderText = when {
        !internet -> noInternetText
        !status -> noConnectText
        else -> enterMessageText
    }

    val placeholderTextColor =
        if (enabled) {
            AppTheme.colors.text.copy(alpha = .5f)
        } else {
            AppTheme.colors.text.copy(alpha = .3f)
        }

    val shape = RoundedCornerShape(size = 16.dp)

    NeuPressedSurface(
        modifier = modifier
            .padding(vertical = verticalPadding),
        elevation = 8.dp,
        corner = 24.dp,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .clip(shape = shape),
            value = input,
            onValueChange = onInputChange,
            enabled = enabled,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                textColor = AppTheme.colors.text,
                disabledTextColor = AppTheme.colors.text,
            ),
            keyboardActions = KeyboardActions(
                onSend = { sendButtonAction() },
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            singleLine = true,
            placeholder = {
                Text(
                    text = placeholderText,
                    fontWeight = FontWeight.Light,
                    color = placeholderTextColor,
                )
            },
            shape = shape,
            trailingIcon = {
                AnimatedVisibility(
                    visible = input.isNotBlank(),
                    enter = fadeIn(animationSpec = tween(durationMillis = 200))
                            + scaleIn(animationSpec = tween(durationMillis = 200)),
                    exit = fadeOut(animationSpec = tween(durationMillis = 200))
                            + scaleOut(animationSpec = tween(durationMillis = 200)),
                ) {
                    Icon(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(percent = 50))
                            .clickableClean { onInputChange("") }
                            .padding(all = 8.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = AppTheme.colors.text,
                    )
                }
            }
        )
    }
}