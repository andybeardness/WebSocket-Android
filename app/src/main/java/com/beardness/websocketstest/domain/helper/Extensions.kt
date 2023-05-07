package com.beardness.websocketstest.domain.helper

suspend fun String.ifNotBlank(action: suspend (current: String) -> Unit) =
    if (this.isNotBlank()) {
        action(this)
    } else {
        Unit
    }