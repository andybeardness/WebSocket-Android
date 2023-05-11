package com.beardness.websocketstest.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.beardness.websocketstest.ui.compose.component.spacer.NeuSpacerColumn
import com.beardness.websocketstest.ui.compose.widget.connection.NeuConnectionWidget
import com.beardness.websocketstest.ui.compose.widget.loading.NeuLoadingWidget
import com.beardness.websocketstest.ui.compose.widget.message.NeuMessagesWidget
import com.beardness.websocketstest.ui.compose.widget.send.NeuSendWidget
import com.beardness.websocketstest.ui.compose.widget.status.NeuStatusWidget
import com.beardness.websocketstest.ui.compose.widget.toolbar.NeuToolbarWidget
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    haptic: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    val internet by viewModel.internet.collectAsState(initial = false)
    val status by viewModel.status.collectAsState(initial = false)
    val loading by viewModel.loading.collectAsState(initial = false)
    val message by viewModel.message.collectAsState(initial = emptyList())

    var url by remember { mutableStateOf("") }
    var input by remember { mutableStateOf("") }

    val onUrlChanged: (new: String) -> Unit = { new ->
        url = new
        viewModel.updateUrl(updated = new)
    }
    
    LaunchedEffect(key1 = Unit) {
        launch {
            viewModel.restoreUrlEvent.collectLatest { restored ->
                url = restored
            }
        }
    }

    val connectButtonAction: () -> Unit = {
        haptic()
        if (internet) {
            viewModel.connect(url = url)
        }
    }

    val disconnectButtonAction: () -> Unit = {
        haptic()
        if (internet && status) {
            viewModel.disconnect()
        }
    }

    val sendAction: () -> Unit = {
        haptic()
        viewModel.message(text = input)
        focusManager.clearFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        NeuToolbarWidget(
            internet = internet,
            url = url,
            onUrlChanged = onUrlChanged,
        )

        NeuSpacerColumn(size = 8.dp)

        NeuConnectionWidget(
            internet = internet,
            status = status,
            connectButtonAction = connectButtonAction,
            disconnectButtonAction = disconnectButtonAction,
        )

        NeuSpacerColumn(size = 8.dp)

        NeuStatusWidget(
            internet = internet,
            status = status,
        )

        NeuSpacerColumn(size = 8.dp)

        NeuLoadingWidget(
            loading = loading,
        )

        NeuMessagesWidget(
            modifier = Modifier.weight(weight = 1f),
            message = message,
        )

        NeuSpacerColumn(size = 8.dp)

        NeuSendWidget(
            internet = internet,
            status = status,
            input = input,
            onInputChange = { new -> input = new },
            onClickSend = sendAction,
        )

        NeuSpacerColumn(size = 16.dp)
    }
}
