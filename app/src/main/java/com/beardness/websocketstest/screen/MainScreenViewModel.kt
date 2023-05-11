package com.beardness.websocketstest.screen

import com.beardness.websocketstest.domain.OkHttpWsClient
import com.beardness.websocketstest.domain.dto.FailureDto
import com.beardness.websocketstest.domain.dto.MessageDto
import com.beardness.websocketstest.domain.helper.Timer
import com.beardness.websocketstest.domain.helper.ifNotBlank
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.WebSocket

class MainScreenViewModel(
    private val timer: Timer = Timer()
) {
    private val scope = CoroutineScope(context = Dispatchers.IO)

    private val _internet = MutableStateFlow<Boolean>(value = false)
    val internet = _internet.asStateFlow()

    private val _status = MutableStateFlow<Boolean>(value = false)
    val status = _status.asStateFlow()

    private val _message = MutableStateFlow<List<MessageDto>>(value = emptyList())
    val message = _message.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(value = false)
    val loading = _loading.asStateFlow()

    private val _failure = MutableStateFlow<FailureDto?>(value = null)
    val failure = _failure.asStateFlow()

    private val _restoreUrlEvent = MutableStateFlow<String>(value = "")
    val restoreUrlEvent = _restoreUrlEvent.asStateFlow()

    private val _currentUrl = MutableStateFlow<String>(value = "")
    val currentUrl = _currentUrl.asStateFlow()

    private val setStatus: (status: Boolean) -> Unit = { status ->
        scope.launch {
            _status.emit(value = status)
        }
    }

    private val addMessage: (status: Boolean, text: String) -> Unit = { status, text ->
        if (_status.value) {
            scope.launch {
                _message.appendEmit(
                    element = MessageDto(
                        status = status,
                        text = text,
                        datetime = timer.now()
                    ),
                )
            }
        }
    }

    private val onFailure: (message: String) -> Unit = { message ->
        scope.launch {
            _status.emit(value = false)
            _failure.emit(value = FailureDto(message = message))
        }
    }

    private val stopLoading: () -> Unit = {
        scope.launch {
            _loading.emit(value = false)
        }
    }

    private val okHttpWsClient = OkHttpWsClient(
        setStatus = setStatus,
        addMessage = addMessage,
        onFailure = onFailure,
        stopLoading = stopLoading,
    )

    private var websocket: WebSocket? = null

    fun internet(availability: Boolean) {
        scope.launch {
            _internet.emit(value = availability)
            _status.emit(value = false)
        }
    }

    fun restoreUrl(restored: String) {
        scope.launch {
            _restoreUrlEvent.emit(value = restored)
        }
    }

    fun updateUrl(updated: String) {
        scope.launch {
            _currentUrl.emit(value = updated)
        }
    }

    fun connect(url: String) {
        scope.launch {
            try {
                _loading.emit(value = true)
                websocket = okHttpWsClient.websocket(url = url)
            } catch (t: Throwable) {
                _failure.emit(value = FailureDto(message = t.message.orEmpty()))
                _loading.emit(value = false)
            }
        }
    }

    fun disconnect() {
        scope.launch {
            _loading.emit(value = true)
            websocket?.close(code = 1000, "Closed manually")
        }
    }

    fun message(text: String) {
        scope.launch {
            text.ifNotBlank { currentText ->
                _loading.emit(value = true)

                val trimmed = currentText.trim()

                websocket?.send(text = trimmed)

                _message.appendEmit(
                    element = MessageDto(
                        status = true,
                        text = trimmed,
                        datetime = timer.now(),
                    )
                )
            }
        }
    }

    fun stop() {
        websocket?.close(code = 1000, "Closed manually")
        websocket = null
    }

    private suspend fun <T> MutableStateFlow<List<T>>.appendEmit(element: T) {
        this.emit(value = this.value.toMutableList().apply { add(index = 0, element = element) })
    }
}