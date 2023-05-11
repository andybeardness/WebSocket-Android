package com.beardness.websocketstest.ui.compose.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.component.surface.NeuTapSurface
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuConnectButton(
    modifier: Modifier,
    state: NeuButtonState,
    title: String,
    onClick: () -> Unit,
) {
    val textColor = when (state) {
        NeuButtonState.ON -> AppTheme.colors.text
        NeuButtonState.OFF -> AppTheme.colors.text.copy(alpha = .3f)
    }

    NeuTapSurface(
        modifier = modifier,
        color = AppTheme.colors.background,
        corner = 24.dp,
        elevation = 6.dp,
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .padding(all = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = textColor,
            )
        }
    }
}