package com.example.weatherapp.views.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.theme.Blue
import com.example.weatherapp.ui.theme.BlueDark
import com.example.weatherapp.ui.theme.Dimens
import com.example.weatherapp.views.components.DailyForecast
import com.example.weatherapp.views.components.HourlyForecast
import com.example.weatherapp.views.components.WeatherHeader
import com.example.weatherapp.views.components.WeatherInfo
import com.example.weatherapp.views.components.WeatherInfoDual
import com.example.weatherapp.views.viewmodels.WeatherVM

@Composable
fun WeatherScreen(
    weatherVM: WeatherVM = viewModel()
) {
    val context = LocalContext.current

    val locationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val fineLocationGranted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            val coarseLocationGranted =
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (fineLocationGranted || coarseLocationGranted) {

                weatherVM.getWeatherForCurrentLocation(context)

            } else {

                weatherVM.onLocationPermissionDenied()
            }
        }

    LaunchedEffect(Unit) {
        val fineLocationGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationGranted =
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        if (fineLocationGranted || coarseLocationGranted) {
            weatherVM.getWeatherForCurrentLocation(context)
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
    AnimatedContent(
        targetState = weatherVM.isLoading,
        label = "WeatherContent"
    ) { isLoading ->

        if (isLoading) {
            WeatherLoadingScreen()
        } else if (weatherVM.locationPermissionDenied) {

            LocationPermissionScreen(
                onAllowLocation = {

                    locationPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            )

        } else if (weatherVM.errorMessage != null) {

            WeatherErrorScreen(
                message = weatherVM.errorMessage!!,
                onRetry = {
                    weatherVM.getWeatherForCurrentLocation(context)
                }
            )

        } else {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Blue, BlueDark)
                        )
                    )
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(bottom = Dimens.dp24)
                    .navigationBarsPadding()
            )
            {

                WeatherHeader(
                    weather = weatherVM.currentWeather
                )

                Spacer(
                    modifier = Modifier.height(Dimens.dp24)
                )

                HourlyForecast(
                    forecasts = weatherVM.hourlyWeather
                )

                Spacer(
                    modifier = Modifier.height(Dimens.dp24)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.dp24)
                ) {

                    DailyForecast(
                        forecasts = weatherVM.dailyWeather
                    )

                    Spacer(
                        modifier = Modifier.height(Dimens.dp24)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        WeatherInfo(
                            icon = Icons.Default.WbSunny,
                            title = "UV index",
                            value = weatherVM.currentWeather.uvIndex?.toString() ?: "--",
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(Dimens.dp16))
                        WeatherInfo(
                            icon = Icons.Default.WaterDrop,
                            title = "Humidity",
                            value = "${weatherVM.currentWeather.humidity ?: "--"}%",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(Dimens.dp16)
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        WeatherInfo(
                            icon = Icons.Default.Air,
                            title = "Wind",
                            value = "${weatherVM.currentWeather.windSpeed?.toString() ?: "--"} km/h",
                            modifier = Modifier.weight(0.8f)
                        )
                        Spacer(modifier = Modifier.width(Dimens.dp20))
                        WeatherInfoDual(
                            iconA = Icons.Default.WbSunny,
                            titleA = "Sunrise",
                            valueA = weatherVM.currentWeather.sunrise ?: "--",
                            iconB = Icons.Default.WbTwilight,
                            titleB = "Sunset",
                            valueB = weatherVM.currentWeather.sunset ?: "--",
                            modifier = Modifier.weight(1.2f)
                        )
                    }
                }
            }
        }
    }
}