package com.beardness.websocketstest.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.R
import com.beardness.websocketstest.domain.dto.MessageDto
import com.beardness.websocketstest.ui.widget.component.MessageComponent

@Composable
fun MessagesWidget(
    modifier: Modifier,
    message: List<MessageDto>
) {
    val youText = stringResource(id = R.string.you)
    val serverText = stringResource(id = R.string.server)

    val clientColor = MaterialTheme.colorScheme.primary
    val serverColor = MaterialTheme.colorScheme.secondary

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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

            MessageComponent(
                status = status,
                statusColor = statusColor,
                text = text,
                datetime = datetime,
            )
        }
    }
}