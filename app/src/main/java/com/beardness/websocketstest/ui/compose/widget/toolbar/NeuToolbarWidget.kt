package com.beardness.websocketstest.ui.compose.widget.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.widget.input.NeuTextInput
import com.beardness.websocketstest.ui.compose.component.logo.NeuLogoComponent
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuToolbarWidget(
    internet: Boolean,
    url: String,
    onUrlChanged: (new: String) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 64.dp)
                .background(color = AppTheme.colors.background),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NeuLogoComponent(
                modifier = Modifier
                    .weight(weight = 1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 80.dp)
                .background(color = AppTheme.colors.background),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NeuTextInput(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalPadding = 8.dp,
                internet = internet,
                status = true,
                input = url,
                onInputChange = onUrlChanged,
                sendButtonAction = {},
            )
        }
    }
}