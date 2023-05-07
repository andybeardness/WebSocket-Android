package com.beardness.websocketstest.ui.widget.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import  com.beardness.websocketstest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputComponent(
    modifier: Modifier,
    internet: Boolean,
    status: Boolean,
    input: String,
    onInputChanged: (new: String) -> Unit,
    sendButtonAction: () -> Unit,
) {
    val enterMessageText = stringResource(id = R.string.enter_message)
    val noConnectText = stringResource(id = R.string.no_connect)
    val noInternetText = stringResource(id = R.string.no_internet)

    val textFieldShape = RoundedCornerShape(percent = 50)

    val enableColor = MaterialTheme.colorScheme.primary
    val enableBackgroundColor = enableColor.copy(alpha = .2f)
    val enableColorAlpha = enableColor.copy(alpha = .75f)

    val disableColor = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f)
    val disableBackgroundColor = disableColor.copy(alpha = .1f)

    val transparentColor = Color.Transparent
    val messageColor = MaterialTheme.colorScheme.onBackground

    val colorAnimationSpec = tween<Color>(durationMillis = 200)

    val textFieldBackgroundColor by animateColorAsState(
        targetValue = when {
            !internet -> disableBackgroundColor
            status -> enableBackgroundColor
            else -> disableBackgroundColor
        },
        animationSpec = colorAnimationSpec,
    )

    val textFieldIndicatorColor = Color.Transparent

    val textFieldColor by animateColorAsState(
        targetValue = when {
            !internet -> transparentColor
            status -> messageColor
            else -> transparentColor
        },
        animationSpec = colorAnimationSpec,
    )

    val textFieldPlaceholder = when {
        internet && status -> enterMessageText
        internet -> noConnectText
        else -> noInternetText
    }

    val textFieldPlaceholderColor by animateColorAsState(
        targetValue = when {
            !internet -> messageColor
            status -> enableColorAlpha
            else -> messageColor
        },
        animationSpec = colorAnimationSpec,
    )

    TextField(
        modifier = modifier,
        value = input,
        onValueChange = onInputChanged,
        enabled = status,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = textFieldBackgroundColor,
            focusedIndicatorColor = textFieldIndicatorColor,
            disabledIndicatorColor = textFieldIndicatorColor,
            unfocusedIndicatorColor = textFieldIndicatorColor,
            textColor = textFieldColor,
            disabledTextColor = textFieldColor,
        ),
        keyboardActions = KeyboardActions(
            onSend = { sendButtonAction() },
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        singleLine = true,
        placeholder = {
            Text(
                text = textFieldPlaceholder,
                fontWeight = FontWeight.Thin,
                color = textFieldPlaceholderColor,
            )
        },
        shape = textFieldShape,
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .clickable { onInputChanged("") }
                    .padding(all = 8.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = textFieldColor,
            )
        }
    )
}