package com.beardness.websocketstest.ui.widget.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrlInputComponent(
    modifier: Modifier,
    internet: Boolean,
    url: String,
    onUrlChanged: (new: String) -> Unit,
) {
    val enableColor = MaterialTheme.colorScheme.primary
    val enableBackgroundColor = enableColor.copy(alpha = .2f)
    val enableColorAlpha = enableColor.copy(alpha = .75f)

    val textFieldIndicatorColor = Color.Transparent

    val messageColor = MaterialTheme.colorScheme.onBackground

    val textFieldColor = messageColor
    val textFieldBackgroundColor = enableBackgroundColor
    val textFieldPlaceholderColor = enableColorAlpha

    val textFieldPlaceholder = when {
        internet -> "wss://..."
        else -> "No internet"
    }

    val textFieldShape = RoundedCornerShape(percent = 50)

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = url,
        onValueChange = onUrlChanged,
        enabled = internet,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = textFieldBackgroundColor,
            focusedIndicatorColor = textFieldIndicatorColor,
            disabledIndicatorColor = textFieldIndicatorColor,
            unfocusedIndicatorColor = textFieldIndicatorColor,
            textColor = textFieldColor,
            disabledTextColor = textFieldColor,
        ),
        singleLine = true,
        placeholder = {
            Text(
                text = textFieldPlaceholder,
                fontWeight = FontWeight.Thin,
                color = textFieldPlaceholderColor,
            )
        },
        shape = textFieldShape,
        textStyle = TextStyle(
            color = textFieldColor,
            fontFamily = FontFamily.Monospace,
        )
    )
}