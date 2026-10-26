
package com.seyda.havadurumum.repository

import com.seyda.havadurumum.model.WeatherResponse
import com.seyda.havadurumum.network.RetrofitClient

class WeatherRepository {

    private val weatherApi = RetrofitClient.weatherApi

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): WeatherResponse {

        return weatherApi.getCurrentWeather(
            latitude = latitude,
            longitude = longitude
        )
    }
}
