package com.example.weatherapp.repository

import com.example.weatherapp.dto.WeatherResponse
import com.example.weatherapp.network.WeatherApi

class WeatherRepository {

    suspend fun getWeatherData(
        latitude: Double, longitude: Double
    ): WeatherResponse {
        return WeatherApi.retrofitService.getWeather(
            latitude = latitude,
            longitude = longitude,
            current = "temperature_2m,apparent_temperature,relative_humidity_2m,precipitation,weather_code,wind_speed_10m,uv_index",
            hourly = "temperature_2m,precipitation_probability,weather_code",
            daily = "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max,sunrise,sunset",
            timezone = "auto"
        )
    }
}