package com.example.weatherapp.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.example.weatherapp.ui.theme.Dimens
import com.example.weatherapp.ui.theme.WeatherCard
import com.example.weatherapp.ui.theme.White
import com.example.weatherapp.ui.theme.WhiteSecondary

@Composable
fun WeatherInfo(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.InfoCardHeight),
        shape = RoundedCornerShape(
            Dimens.dp16
        ),
        colors = CardDefaults.cardColors(
            containerColor = WeatherCard
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                Dimens.dp8
            )
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(
                    Dimens.IconMedium
                ),
                tint = White
            )

            Text(
                text = title,
                color = WhiteSecondary,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = White,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun WeatherInfoDual(
    iconA: ImageVector,
    titleA: String,
    valueA: String,
    iconB: ImageVector,
    titleB: String,
    valueB: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.InfoCardHeight),
        shape = RoundedCornerShape(
            Dimens.dp16
        ),
        colors = CardDefaults.cardColors(
            containerColor = WeatherCard
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.dp8),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            listOf(
                Triple(iconA, titleA, valueA),
                Triple(iconB, titleB, valueB)
            ).forEach { (icon, title, value) ->

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        modifier = Modifier.size(Dimens.IconSmall),
                        tint = White
                    )

                    Spacer(modifier = Modifier.height(Dimens.dp4))

                    Text(
                        text = title,
                        color = WhiteSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(Dimens.dp4))

                    Text(
                        text = value,
                        color = White,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}