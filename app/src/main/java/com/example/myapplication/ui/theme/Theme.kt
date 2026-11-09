package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ColorTheme.logoGreen,
    secondary = ColorTheme.lightGreen,
    background = ColorTheme.darkBackground,
    onBackground = ColorTheme.darkOnBackground,
    surface = ColorTheme.darkSurface,
    onSurface = ColorTheme.darkOnBackground,
    surfaceVariant = ColorTheme.darkSurfaceVariant,
    onSurfaceVariant = ColorTheme.darkOnSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = ColorTheme.logoGreen,
    secondary = ColorTheme.lightGreen,
    background = ColorTheme.lightBackground,
    onBackground = ColorTheme.lightOnBackground,
    surface = ColorTheme.lightSurface,
    onSurface = ColorTheme.lightOnBackground,
    surfaceVariant = ColorTheme.lightSurfaceVariant,
    onSurfaceVariant = ColorTheme.lightOnSurfaceVariant
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}
