package com.example.weatherapp.dto

data class CurrentWeather(
    val location: String?,
    val temperature: Int?,
    val highTemperature: Int?,
    val lowTemperature: Int?,
    val feelsLike: Int?,
    val condition: String?,
    val windSpeed: Double?,
    val humidity: Int?,
    val uvIndex: Double?,
    val precipitation: Double?,
    val sunrise: String?,
    val sunset: String?
)
