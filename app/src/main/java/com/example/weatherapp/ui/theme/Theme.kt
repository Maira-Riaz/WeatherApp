package com.example.weatherapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val WeatherColorScheme = lightColorScheme(

    primary = Blue,
    secondary = BlueDark,
    background = Blue,
    surface = Blue,

    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun WeatherAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WeatherColorScheme,
        typography = Typography,
        content = content
    )
}