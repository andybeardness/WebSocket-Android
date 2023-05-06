package com.beardness.websocketstest.domain.helper

import org.joda.time.LocalDateTime

private const val FORMAT = "yyyy-MM-dd kk:mm:ss"

class Timer {
    fun now(): String {
        return LocalDateTime.now().toString(FORMAT)
    }
}