package com.example.weatherforecast.model

data class City(
    val id: Long?,
    val name: String?,
    val coord: Coord?,
    val country: String?,
    val population: Long?,
    val timezone: Long?
)
