package com.example.weatherapp.dto

data class DailyWeather(
    val day: String?,
    val highTemperature: Int?,
    val lowTemperature: Int?,
    val precipitation: Int?,
    val dayCondition: WeatherCondition?,
    val nightCondition: WeatherCondition?
)
