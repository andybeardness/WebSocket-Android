package com.beardness.websocketstest.ui.compose.widget.connection

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.R
import com.beardness.websocketstest.ui.compose.component.button.NeuButtonState
import com.beardness.websocketstest.ui.compose.component.button.NeuConnectButton
import com.beardness.websocketstest.ui.compose.component.spacer.NeuSpacerRow

@Composable
fun NeuConnectionWidget(
    internet: Boolean,
    status: Boolean,
    connectButtonAction: () -> Unit,
    disconnectButtonAction: () -> Unit,
) {
    val connectText = stringResource(id = R.string.connect)
    val disconnectText = stringResource(id = R.string.disconnect)

    val connectedButtonState = when {
        !internet -> NeuButtonState.OFF
        status -> NeuButtonState.OFF
        else -> NeuButtonState.ON
    }

    val disconnectedButtonState = when {
        !internet -> NeuButtonState.OFF
        status -> NeuButtonState.ON
        else -> NeuButtonState.OFF
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        NeuConnectButton(
            modifier = Modifier
                .weight(weight = 1f),
            state = connectedButtonState,
            title = connectText,
            onClick = connectButtonAction,
        )

        NeuSpacerRow(size = 16.dp)

        NeuConnectButton(
            modifier = Modifier
                .weight(weight = 1f),
            state = disconnectedButtonState,
            title = disconnectText,
            onClick = disconnectButtonAction,
        )
    }
}