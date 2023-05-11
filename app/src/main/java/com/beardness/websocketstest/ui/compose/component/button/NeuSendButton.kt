package com.beardness.websocketstest.ui.compose.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.component.surface.NeuTapSurface
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuSendButton(
    onClick: () -> Unit,
) {
    NeuTapSurface(
        modifier = Modifier,
        color = AppTheme.colors.background,
        corner = 24.dp,
        elevation = 6.dp,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .size(size = 80.dp)
                .padding(all = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                tint = AppTheme.colors.text,
            )
        }
    }
}