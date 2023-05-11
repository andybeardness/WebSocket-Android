package com.beardness.websocketstest.domain

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WsListener(
    private val setStatus: (status: Boolean) -> Unit,
    private val addMessage: (status: Boolean, text: String) -> Unit,
    private val onFailure: (message: String) -> Unit,
    private val stopLoading: () -> Unit,
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        setStatus(true)
        webSocket.send("Android Device Connected")
        stopLoading()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        addMessage(false, text)
        stopLoading()
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        stopLoading()
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        setStatus(false)
        stopLoading()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        val failureMessage = t.message ?: ""
        onFailure(failureMessage)
        stopLoading()
    }
}

