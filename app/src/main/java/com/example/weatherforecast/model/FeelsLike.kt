package com.example.weatherforecast.model

import java.io.Serializable

data class FeelsLike (
    val day: Double?,
    val night: Double?,
    val eve: Double?,
    val morn: Double?
)