package com.beardness.websocketstest.ui.compose.widget.send

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.component.button.NeuSendButton
import com.beardness.websocketstest.ui.compose.component.spacer.NeuSpacerRow
import com.beardness.websocketstest.ui.compose.widget.input.NeuTextInput

@Composable
fun NeuSendWidget(
    internet: Boolean,
    status: Boolean,
    input: String,
    onInputChange: (new: String) -> Unit,
    onClickSend: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 80.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NeuTextInput(
            modifier = Modifier.weight(weight = 1f),
            verticalPadding = 0.dp,
            internet = internet,
            status = status,
            input = input,
            onInputChange = onInputChange,
            sendButtonAction = onClickSend,
        )

        NeuSpacerRow(
            size = 16.dp
        )

        NeuSendButton(
            onClick = onClickSend
        )
    }
}