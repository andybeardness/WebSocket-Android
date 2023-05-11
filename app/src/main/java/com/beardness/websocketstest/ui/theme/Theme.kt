package com.beardness.websocketstest.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.beardness.websocketstest.ui.theme.color.AppColors
import com.beardness.websocketstest.ui.theme.color.LocalAppColors
import com.beardness.websocketstest.ui.theme.color.appColorsDark
import com.beardness.websocketstest.ui.theme.color.material.MaterialPalette
import com.beardness.websocketstest.ui.theme.color.appColorsLight
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val localContext = LocalContext.current

    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme =
        when {
            dynamicColor && darkTheme -> dynamicDarkColorScheme(context = localContext)
            dynamicColor && !darkTheme -> dynamicLightColorScheme(context = localContext)
            !darkTheme -> MaterialPalette.LightColorScheme
            else -> MaterialPalette.DarkColorScheme
        }

    val appColors =
        if (darkTheme) {
            appColorsDark
        } else {
            appColorsLight
        }

    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = appColors.background)
        setNavigationBarColor(color = appColors.background)
    }

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}