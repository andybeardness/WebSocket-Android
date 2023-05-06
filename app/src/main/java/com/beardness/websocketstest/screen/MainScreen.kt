package com.beardness.websocketstest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.beardness.websocketstest.ui.widget.*
import com.beardness.websocketstest.ui.widget.component.SpacerVerticalComponent

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
) {
    val focusManager = LocalFocusManager.current

    val internet by viewModel.internet.collectAsState(initial = false)
    val url by viewModel.url.collectAsState(initial = "")
    val open by viewModel.toolbarOpen.collectAsState(initial = false)
    val status by viewModel.status.collectAsState(initial = false)
    val loading by viewModel.loading.collectAsState(initial = false)
    val message by viewModel.message.collectAsState(initial = emptyList())
    val input by viewModel.input.collectAsState(initial = "")

    val connectButtonAction: () -> Unit = when {
        internet && status -> {
            { /* Do nothing */ }
        }
        internet -> {
            { viewModel.connect() }
        }
        else -> {
            { /* Do nothing */ }
        }
    }

    val disconnectButtonAction: () -> Unit = when {
        internet && status -> {
            { viewModel.disconnect() }
        }
        internet -> {
            { /* Do nothing */ }
        }
        else -> {
            { /* Do nothing */ }
        }
    }

    val sendAction: () -> Unit = {
        viewModel.message(text = input)
        focusManager.clearFocus()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ToolbarWidget(
            internet = internet,
            url = url,
            onUrlChanged = { new -> viewModel.url(update = new) },
            open = open,
            onClickOpen = { viewModel.toolbarSwitch() }
        )

        SpacerVerticalComponent()

        ConnectionWidget(
            internet = internet,
            status = status,
            connectButtonAction = connectButtonAction,
            disconnectButtonAction = disconnectButtonAction,
        )

        SpacerVerticalComponent()

        StatusWidget(
            internet = internet,
            status = status,
        )

        SpacerVerticalComponent()

        LoadingWidget(
            loading = loading,
        )

        MessagesWidget(
            modifier = Modifier.weight(weight = 1f),
            message = message,
        )

        SpacerVerticalComponent()

        SendWidget(
            modifier = Modifier.weight(weight = 1f),
            internet = internet,
            status = status,
            input = input,
            onInputChanged = { new -> viewModel.input(update = new) },
            onClickSend = sendAction,
        )

        SpacerVerticalComponent()
    }
}
