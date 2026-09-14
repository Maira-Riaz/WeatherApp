package com.example.weatherapp.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.dto.HourlyWeather
import com.example.weatherapp.dto.WeatherCondition
import com.example.weatherapp.ui.theme.Dimens
import com.example.weatherapp.ui.theme.WeatherCard
import com.example.weatherapp.ui.theme.White
import com.example.weatherapp.ui.theme.WhiteSecondary
import com.example.weatherapp.ui.theme.Yellow

private fun iconFor(condition: WeatherCondition?): ImageVector = when (condition) {
    WeatherCondition.CLOUDY -> Icons.Default.Cloud
    WeatherCondition.RAINY -> Icons.Default.WaterDrop
    WeatherCondition.NIGHT -> Icons.Default.NightsStay
    else -> Icons.Default.WbSunny
}

private fun tintFor(condition: WeatherCondition?): Color = when (condition) {
    WeatherCondition.CLOUDY -> WhiteSecondary
    WeatherCondition.RAINY -> White
    else -> Yellow
}

@Composable
fun HourlyForecast(
    forecasts: List<HourlyWeather>
) {
    Row(
        modifier = Modifier
            .padding(horizontal = Dimens.dp24)
            .clip(RoundedCornerShape(Dimens.dp20))
            .background(WeatherCard)
            .padding(Dimens.dp12)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(Dimens.dp8)
    ) {

        forecasts.forEach { forecast ->

            Column(
                modifier = Modifier.width(Dimens.HourlyItemWidth),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.dp6)
            ) {
                Text(
                    text = forecast.time ?: "--",
                    color = WhiteSecondary,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )

                Icon(
                    imageVector = iconFor(forecast.condition),
                    contentDescription = "Weather",
                    modifier = Modifier.size(Dimens.IconMedium),
                    tint = tintFor(forecast.condition)
                )

                if (forecast.isSunset == true) {
                    Text(
                        text = "Sunset",
                        color = WhiteSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Text(
                        text = "${forecast.temperature ?: "--"}°",
                        color = White,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = "Precipitation",
                        tint = WhiteSecondary,
                        modifier = Modifier.size(Dimens.dp14)
                    )

                    Spacer(
                        modifier = Modifier.width(Dimens.dp2)
                    )

                    Text(
                        text = "${forecast.precipitation ?: 0}%",
                        color = WhiteSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}