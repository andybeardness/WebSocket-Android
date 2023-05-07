package com.beardness.websocketstest.ui.widget.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UrlInputComponent(
    modifier: Modifier,
    internet: Boolean,
    url: String,
    onUrlChanged: (new: String) -> Unit,
) {
    val wssPlaceholderText = stringResource(id = R.string.wss_placeholder_msg)
    val noInternetText = stringResource(id = R.string.no_internet)

    val enableColor = MaterialTheme.colorScheme.primary
    val enableBackgroundColor = enableColor.copy(alpha = .2f)
    val enableColorAlpha = enableColor.copy(alpha = .75f)

    val textFieldIndicatorColor = Color.Transparent

    val messageColor = MaterialTheme.colorScheme.onBackground

    val textFieldColor = messageColor
    val textFieldBackgroundColor = enableBackgroundColor
    val textFieldPlaceholderColor = enableColorAlpha

    val textFieldPlaceholder = when {
        internet -> wssPlaceholderText
        else -> noInternetText
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
        ),
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(percent = 50))
                    .clickable { onUrlChanged("") }
                    .padding(all = 8.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = textFieldColor,
            )
        },
    )
}