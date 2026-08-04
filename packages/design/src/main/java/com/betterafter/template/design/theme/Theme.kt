package com.betterafter.template.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Accent,
    onPrimary = Mist,
    secondary = AccentSoft,
    onSecondary = Ink,
    background = Mist,
    onBackground = Ink,
    surface = Mist,
    onSurface = Ink,
    error = Danger,
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content,
    )
}
