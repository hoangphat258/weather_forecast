package com.example.weatherforecast.repository

import com.example.weatherforecast.api.Resource
import com.example.weatherforecast.api.WeatherApi
import com.example.weatherforecast.model.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
): BaseRepository() {

    suspend fun getWeather(city: String): Resource<WeatherResponse> {
        return callApi { weatherApi.getWeather(city) }
    }

}