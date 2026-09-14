package com.example.weatherapp.dto

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val current: CurrentWeatherResponse,
    val hourly: HourlyWeatherResponse,
    val daily: DailyWeatherResponse
)