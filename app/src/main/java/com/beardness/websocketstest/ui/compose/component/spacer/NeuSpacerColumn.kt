package com.beardness.websocketstest.ui.compose.component.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun NeuSpacerColumn(size: Dp) {
    Spacer(modifier = Modifier.height(height = size))
}