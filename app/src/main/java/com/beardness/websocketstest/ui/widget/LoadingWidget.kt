package com.beardness.websocketstest.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beardness.websocketstest.ui.widget.component.SpacerVerticalComponent

@Composable
fun LoadingWidget(
    loading: Boolean,
) {
    val enableColor = MaterialTheme.colorScheme.primary

    AnimatedVisibility(
        visible = loading,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(),
                color = enableColor,
            )

            SpacerVerticalComponent()
        }
    }
}