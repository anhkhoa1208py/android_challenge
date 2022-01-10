package com.example.challenge.models

data class WeatherModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Data>,
    val message: Double
)