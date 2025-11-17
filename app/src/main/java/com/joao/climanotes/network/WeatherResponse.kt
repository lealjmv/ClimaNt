package com.joao.climanotes.network

data class WeatherResponse(
    val name: String,
    val main: MainInfo,
    val weather: List<WeatherInfo>
)

data class MainInfo(
    val temp: Float
)

data class WeatherInfo(
    val description: String,
    val icon: String
)
