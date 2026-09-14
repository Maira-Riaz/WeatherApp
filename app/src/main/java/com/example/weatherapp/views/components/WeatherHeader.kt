package com.example.weatherapp.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import com.example.weatherapp.helper.formatCurrentDate
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weatherapp.dto.CurrentWeather
import com.example.weatherapp.ui.theme.Dimens
import com.example.weatherapp.ui.theme.White
import com.example.weatherapp.ui.theme.WhiteSecondary

@Composable
fun WeatherHeader(
    weather: CurrentWeather
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp24)
    ) {

        Spacer(
            modifier = Modifier.height(Dimens.dp40)
        )

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = White
            )
        }

        Spacer(
            modifier = Modifier.height(Dimens.dp16)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Column {

                Text(
                    text = "${weather.temperature ?: "--"}°",
                    style = MaterialTheme.typography.displayLarge,
                    color = White
                )

                Text(
                    text = weather.condition ?: "--",
                    style = MaterialTheme.typography.headlineLarge,
                    color = White
                )

                Spacer(
                    modifier = Modifier.height(Dimens.dp16)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = weather.location ?: "Unknown location",
                        style = MaterialTheme.typography.headlineMedium,
                        color = White
                    )

                    Spacer(
                        modifier = Modifier.size(Dimens.dp4)
                    )

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = White,
                        modifier = Modifier.size(Dimens.IconSmall)
                    )
                }

                Text(
                    text = formatCurrentDate(),
                    color = White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(Dimens.dp16)
                )

                Text(
                    text = "${weather.highTemperature ?: "--"}° / " +
                            "${weather.lowTemperature ?: "--"}° " +
                            "Feels like ${weather.feelsLike ?: "--"}°",
                    style = MaterialTheme.typography.bodyMedium,
                    color = WhiteSecondary
                )
            }
            WeatherImage()
        }
    }
}