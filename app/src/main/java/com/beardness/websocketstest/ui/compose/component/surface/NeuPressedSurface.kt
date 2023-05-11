package com.beardness.websocketstest.ui.compose.component.surface

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

private const val SHADOWS_ALPHA = .15f

@Composable
fun NeuPressedSurface(
    modifier: Modifier,
    elevation: Dp,
    corner: Dp,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .neu(
                lightShadowColor = Color.White.copy(alpha = SHADOWS_ALPHA),
                darkShadowColor = Color.Black.copy(alpha = SHADOWS_ALPHA),
                shadowElevation = elevation,
                lightSource = LightSource.LEFT_TOP,
                shape = Pressed(RoundedCorner(radius = corner)),
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(size = corner),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = .06f)
        ),
    ) {
        content()
    }
}