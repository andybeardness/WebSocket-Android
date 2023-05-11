package com.beardness.websocketstest.ui.compose.widget.loading

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.component.spacer.NeuSpacerColumn
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuLoadingWidget(
    loading: Boolean,
) {
    AnimatedVisibility(
        visible = loading,
        enter = fadeIn(animationSpec = tween(durationMillis = 200)),
        exit = fadeOut(animationSpec = tween(durationMillis = 200)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(),
                color = AppTheme.colors.text,
                trackColor = AppTheme.colors.background,
            )

            NeuSpacerColumn(size = 8.dp)
        }
    }
}