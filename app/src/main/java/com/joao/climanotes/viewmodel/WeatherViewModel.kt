package com.joao.climanotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joao.climanotes.repository.WeatherRepository
import com.joao.climanotes.network.WeatherResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    fun loadWeather(city: String) {
        viewModelScope.launch {
            try {
                _weather.value = repository.getWeather(city)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
