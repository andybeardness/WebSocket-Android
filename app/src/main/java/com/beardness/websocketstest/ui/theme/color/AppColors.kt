package com.beardness.websocketstest.ui.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    val background: Color,
    val text: Color,
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        background = Color.Unspecified,
        text = Color.Unspecified,
    )
}

val appColorsLight = AppColors(
    background = Color(color = 0xFF00897B),
    text = Color(color = 0xFFFFFFFF),
)

val appColorsDark = AppColors(
    background = Color(color = 0xFF013a3b),
    text = Color(color = 0xFFFFFFFF),
)