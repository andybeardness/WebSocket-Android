package com.beardness.websocketstest.ui.compose.component.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NeuSpacerRow(size: Dp = 8.dp) {
    Spacer(modifier = Modifier.width(width = size))
}