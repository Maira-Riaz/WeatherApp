package com.example.weatherapp.network

import com.example.weatherapp.dto.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.open-meteo.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,

        @Query("longitude") longitude: Double,

        @Query("current") current: String,

        @Query("hourly") hourly: String,

        @Query("daily") daily: String,

        @Query("timezone") timezone: String
    ): WeatherResponse

}

object WeatherApi {

    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}