package com.beardness.websocketstest.ui.compose.component.message

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuMessageComponent(
    status: String,
    statusColor: Color,
    text: String,
    datetime: String,
) {
    val messageColor = AppTheme.colors.text
    val messageColorAlpha = messageColor.copy(alpha = .3f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = status,
            color = statusColor,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
        )
        Text(
            text = text,
            color = messageColor,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
        )
        Text(
            text = datetime,
            color = messageColorAlpha,
            fontWeight = FontWeight.Thin,
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp,
        )
    }
}