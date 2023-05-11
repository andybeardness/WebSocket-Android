package com.beardness.websocketstest.ui.compose.widget.message

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beardness.websocketstest.R
import com.beardness.websocketstest.domain.dto.MessageDto
import com.beardness.websocketstest.ui.compose.component.message.NeuMessageComponent
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuMessagesWidget(
    modifier: Modifier,
    message: List<MessageDto>
) {
    val youText = stringResource(id = R.string.you)
    val serverText = stringResource(id = R.string.server)

    val serverColor = AppTheme.colors.text
    val clientColor = serverColor.copy(alpha = .3f)

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(message) { element ->
            val status =
                if (element.status) {
                    youText
                } else {
                    serverText
                }

            val statusColor =
                if (element.status) {
                    clientColor
                } else {
                    serverColor
                }

            val text = element.text

            val datetime = element.datetime

            NeuMessageComponent(
                status = status,
                statusColor = statusColor,
                text = text,
                datetime = datetime,
            )
        }
    }
}