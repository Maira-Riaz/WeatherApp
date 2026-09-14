package com.example.weatherapp.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.Dimens

@Composable
fun WeatherImage() {

    Box(
        modifier = Modifier.size(
            width = Dimens.IllustrationWidth,
            height = Dimens.IllustrationHeight
        )
    ) {
        Image(
            painter = painterResource(R.drawable.sunny_cloud),
            contentDescription = "Sunny weather",
            modifier = Modifier.size(
                width = Dimens.IllustrationWidth,
                height = Dimens.IllustrationHeight
            )
        )
    }
}