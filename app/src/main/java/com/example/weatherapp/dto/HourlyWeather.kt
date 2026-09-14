package com.example.weatherapp.dto

data class HourlyWeather(
    val time: String?,
    val temperature: Int?,
    val precipitation: Int?,
    val condition: WeatherCondition?,
    val isSunset: Boolean? = false
)
