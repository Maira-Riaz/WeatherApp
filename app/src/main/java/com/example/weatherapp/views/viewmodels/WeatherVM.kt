package com.example.weatherapp.views.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.dto.CurrentWeather
import com.example.weatherapp.dto.DailyWeather
import com.example.weatherapp.dto.HourlyWeather
import com.example.weatherapp.helper.fetchCurrentLocation
import com.example.weatherapp.helper.getCityName
import com.example.weatherapp.mapper.toCurrentWeather
import com.example.weatherapp.mapper.toDailyWeather
import com.example.weatherapp.mapper.toHourlyWeather
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class WeatherVM: ViewModel() {
    private val repository = WeatherRepository()
    var isLoading by mutableStateOf(true)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set
    var locationPermissionDenied by mutableStateOf(false)
        private set

    fun onLocationPermissionDenied() {
        locationPermissionDenied = true
        isLoading = false
    }

    fun getWeatherForCurrentLocation(context: Context) {

        locationPermissionDenied = false
        isLoading = true
        errorMessage = null

        fetchCurrentLocation(
            context = context,

            onLocationReceived = { latitude, longitude ->

                Log.d(
                    "WeatherVM",
                    "Location received: lat=$latitude, lon=$longitude"
                )

                val cityName = getCityName(
                    context,
                    latitude,
                    longitude
                )

                viewModelScope.launch {
                    try {

                        val result = repository.getWeatherData(
                            latitude = latitude,
                            longitude = longitude
                        )

                        currentWeather = result.toCurrentWeather().copy(
                            location = cityName
                        )
                        hourlyWeather = result.toHourlyWeather()
                        dailyWeather = result.toDailyWeather()

                        delay(700.milliseconds)

                        isLoading = false

                    } catch (e: Exception) {

                        Log.e(
                            "WeatherVM",
                            "Weather request failed",
                            e
                        )

                        errorMessage = "Unable to get weather data"
                        isLoading = false
                    }
                }
            },

            onLocationError = {

                Log.e(
                    "WeatherVM",
                    "Unable to get current location"
                )

                errorMessage = "Unable to get your location"
                isLoading = false
            }
        )
    }

    var currentWeather by mutableStateOf(
        CurrentWeather(
            location = null,
            temperature = null,
            highTemperature = null,
            lowTemperature = null,
            feelsLike = null,
            condition = null,
            windSpeed = null,
            humidity = null,
            uvIndex = null,
            precipitation = null,
            sunrise = null,
            sunset = null
        )
    )
        private set

    var hourlyWeather by mutableStateOf(emptyList<HourlyWeather>())
        private set

    var dailyWeather by mutableStateOf(emptyList<DailyWeather>())
        private set
}