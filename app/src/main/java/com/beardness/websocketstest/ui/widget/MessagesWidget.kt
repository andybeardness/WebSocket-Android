package com.beardness.websocketstest.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beardness.websocketstest.domain.dto.MessageDto

@Composable
fun MessagesWidget(
    modifier: Modifier,
    message: List<MessageDto>
) {
    val clientColor = MaterialTheme.colorScheme.primary
    val serverColor = MaterialTheme.colorScheme.secondary

    val messageColor = MaterialTheme.colorScheme.onBackground
    val messageColorAlpha = messageColor.copy(alpha = .5f)

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        items(message) { element ->
            val elementStatusText =
                if (element.status) {
                    "YOU"
                } else {
                    "SERVER"
                }

            val ownerColor =
                if (element.status) {
                    clientColor
                } else {
                    serverColor
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 4.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = ownerColor,
                            )
                        ) {
                            append(text = elementStatusText)
                        }

                        append(text = "  ")

                        withStyle(
                            style = SpanStyle(
                                color = messageColor,
                            )
                        ) {
                            append(text = element.text)
                        }

                        append(text = "  ")

                        withStyle(
                            style = SpanStyle(
                                color = messageColorAlpha,
                                fontWeight = FontWeight.Thin,
                            )
                        ) {
                            append(text = element.datetime)
                        }
                    },
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp
                )
            }
        }
    }
}