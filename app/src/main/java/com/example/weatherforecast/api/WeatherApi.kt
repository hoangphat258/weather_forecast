package com.example.weatherforecast.api

import com.example.weatherforecast.model.WeatherResponse
import com.example.weatherforecast.utils.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("daily")
    suspend fun getWeather(
        @Query("q") cityName: String = Constant.DEFAULT_CITY,
        @Query("cnt") numberOfDays: Int = 7,
        @Query("units") unit: String = "metric",
        @Query("appid") appId: String = Constant.APP_ID
    ): Response<WeatherResponse>

}