package com.example.weatherapp.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.dto.DailyWeather
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

@Composable
fun DailyForecast(
    forecasts: List<DailyWeather>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.dp20))
            .background(WeatherCard)
            .padding(Dimens.dp12),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp4)
    ) {

        forecasts.forEach { forecast ->

            Row(
                modifier = Modifier.height(Dimens.DailyItemHeight),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = forecast.day ?: "--",
                    color = White,
                    modifier = Modifier.weight(1f)
                )

                Row(
                    modifier = Modifier.width(
                        Dimens.dp40 + Dimens.dp16
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.WaterDrop,
                        contentDescription = "Precipitation",
                        tint = WhiteSecondary,
                        modifier = Modifier.size(Dimens.IconSmall)
                    )

                    Text(
                        text = "${forecast.precipitation ?: 0}%",
                        color = WhiteSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Icon(
                    imageVector = iconFor(forecast.dayCondition),
                    contentDescription = "Day",
                    modifier = Modifier.size(Dimens.IconMedium),
                    tint = if (
                        forecast.dayCondition == WeatherCondition.SUNNY
                    ) {
                        Yellow
                    } else {
                        White
                    }
                )

                Spacer(
                    modifier = Modifier.width(Dimens.dp8)
                )

                Icon(
                    imageVector = iconFor(forecast.nightCondition),
                    contentDescription = "Night",
                    modifier = Modifier.size(Dimens.IconMedium),
                    tint = if (
                        forecast.nightCondition == WeatherCondition.SUNNY
                    ) {
                        Yellow
                    } else {
                        White
                    }
                )

                Spacer(
                    modifier = Modifier.width(Dimens.dp12)
                )

                Text(
                    text = "${forecast.highTemperature ?: "--"}°",
                    color = White,
                    modifier = Modifier.width(Dimens.dp32),
                    textAlign = TextAlign.End
                )

                Spacer(
                    modifier = Modifier.width(Dimens.dp4)
                )

                Text(
                    text = "${forecast.lowTemperature ?: "--"}°",
                    color = WhiteSecondary,
                    modifier = Modifier.width(Dimens.dp32),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}