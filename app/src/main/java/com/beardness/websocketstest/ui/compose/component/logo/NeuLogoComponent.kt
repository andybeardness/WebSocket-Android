package com.beardness.websocketstest.ui.compose.component.logo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.beardness.websocketstest.ui.theme.AppTheme

@Composable
fun NeuLogoComponent(
    modifier: Modifier,
) {
    val toolbarColor = AppTheme.colors.text
    val toolbarColorAlpha = toolbarColor.copy(alpha = .75f)

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Black,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "W")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "e")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Medium,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "b")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "S")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Normal,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "o")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Light,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "c")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.ExtraLight,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "k")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.ExtraLight,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "e")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Thin,
                    color = toolbarColor,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "t")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Thin,
                    color = toolbarColorAlpha,
                    fontSize = 20.sp,
                )
            ) {
                append(text = "s")
            }
        },
    )
}