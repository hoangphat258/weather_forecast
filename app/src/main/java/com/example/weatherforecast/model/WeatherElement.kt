package com.example.weatherforecast.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherElement(
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: Temp?,

    @SerializedName("feels_like")
    val feelsLike: FeelsLike?,

    val pressure: Long?,
    val humidity: Long?,
    val weather: List<Weather> = ArrayList(),
    val speed: Double?,
    val deg: Long?,
    val gust: Double?,
    val clouds: Long?,
    val pop: Double?,
    val rain: Double?
)