package com.example.weatherapp.helper


import com.example.weatherapp.dto.WeatherCondition

fun getWeatherCondition(code: Int): WeatherCondition {
    return when (code) {
        0 -> WeatherCondition.SUNNY
        1, 2, 3 -> WeatherCondition.CLOUDY
        51, 53, 55, 61, 63 -> WeatherCondition.RAINY
        else -> WeatherCondition.SUNNY
    }
}