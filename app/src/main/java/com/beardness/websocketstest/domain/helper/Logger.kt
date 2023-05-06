package com.beardness.websocketstest.domain.helper

import android.util.Log

object Logger {
    private const val TAG = "LOGLOGLOG"

    fun d(message: String) {
        Log.d(TAG, message)
    }
}