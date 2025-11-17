package com.joao.climanotes.repository

import com.joao.climanotes.network.RetrofitInstance
import com.joao.climanotes.network.WeatherResponse

class WeatherRepository {

    suspend fun getWeather(city: String): WeatherResponse {
        return RetrofitInstance.api.getWeatherByCity(
            city = city,
            apiKey = "0b68d2a934c27b4c1f5ab0de81e56aa4"
        )
    }
}
