package com.beardness.websocketstest.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.beardness.websocketstest.ui.theme.color.Palette
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WebSocketsTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val localContext = LocalContext.current

    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme =
        when {
            dynamicColor && darkTheme -> dynamicDarkColorScheme(context = localContext)
            dynamicColor && !darkTheme -> dynamicLightColorScheme(context = localContext)
            !darkTheme -> Palette.LightColorScheme
            else -> Palette.DarkColorScheme
        }

    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = colorScheme.background)
        setNavigationBarColor(color = colorScheme.background)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}