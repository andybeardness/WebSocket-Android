package com.beardness.websocketstest.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.widget.component.SendButtonComponent
import com.beardness.websocketstest.ui.widget.component.SpacerHorizontalComponent
import com.beardness.websocketstest.ui.widget.component.SpacerVerticalComponent
import com.beardness.websocketstest.ui.widget.component.TextInputComponent

@Composable
fun SendWidget(
    modifier: Modifier,
    internet: Boolean,
    status: Boolean,
    input: String,
    onInputChanged: (new: String) -> Unit,
    onClickSend: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .padding(horizontal = 16.dp),
    ) {
        TextInputComponent(
            modifier = modifier,
            internet = internet,
            status = status,
            input = input,
            onInputChanged = onInputChanged,
            sendButtonAction = onClickSend,
        )

        SpacerHorizontalComponent()

        SendButtonComponent(
            internet = internet,
            status = status,
            action = onClickSend,
        )
    }
}