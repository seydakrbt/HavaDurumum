
package com.seyda.havadurumum.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,

    @SerializedName("current")
    val currentWeather: CurrentWeather
)

data class CurrentWeather(
    @SerializedName("temperature_2m")
    val temperature: Double,

    @SerializedName("relative_humidity_2m")
    val humidity: Int,

    @SerializedName("apparent_temperature")
    val feelsLike: Double,

    @SerializedName("wind_speed_10m")
    val windSpeed: Double,

    @SerializedName("weather_code")
    val weatherCode: Int
)
