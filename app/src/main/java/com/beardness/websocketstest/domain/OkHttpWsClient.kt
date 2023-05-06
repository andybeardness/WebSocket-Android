package com.beardness.websocketstest.domain

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class OkHttpWsClient(
    setStatus: (status: Boolean) -> Unit,
    addMessage: (status: Boolean, text: String) -> Unit,
    onFailure: (message: String) -> Unit,
    stopLoading: () -> Unit,
) {
    private val okHttpClient = OkHttpClient()

    private var wsListener = WsListener(
        setStatus = setStatus,
        addMessage = addMessage,
        onFailure = onFailure,
        stopLoading = stopLoading,
    )

    fun websocket(url: String): WebSocket {
        val request = Request.Builder()
            .url(url = url)
            .build()

        return okHttpClient.newWebSocket(request = request, listener = wsListener)
    }
}