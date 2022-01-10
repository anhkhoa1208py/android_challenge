package com.example.challenge.api

import com.example.challenge.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast/daily")
    suspend fun getWeatherDetail(@Query("q") city : String?, @Query("cnt") cnt : Int?, @Query("appid") appid : String?): WeatherModel?
}