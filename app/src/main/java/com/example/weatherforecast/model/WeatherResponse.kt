package com.example.weatherforecast.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherResponse(
    val city: City?,
    val cod: String?,
    val message: Double?,
    val cnt: Long?,
    @SerializedName("list")
    val list: List<WeatherElement> = ArrayList()
)