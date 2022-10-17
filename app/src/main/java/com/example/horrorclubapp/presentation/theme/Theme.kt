package com.example.horrorclubapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.horrorclubapp.ui.theme.MontserratTypography

private val DarkColorPalette = darkColors(
    primary = dark_black,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = white,
    primaryVariant = Purple700,
    secondary = Teal200
)

@Composable
fun HorrorClubAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MontserratTypography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun SplashScreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = MontserratTypography,
        shapes = Shapes,
        content = content
    )
}