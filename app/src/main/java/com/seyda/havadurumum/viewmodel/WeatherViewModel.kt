
package com.seyda.havadurumum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seyda.havadurumum.model.WeatherResponse
import com.seyda.havadurumum.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException


class WeatherViewModel : ViewModel() {

    private val repository = WeatherRepository()

    private var weatherJob: Job? = null

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    fun loadWeather(latitude: Double, longitude: Double) {

        // Önceki hava durumu isteğini iptal et
        weatherJob?.cancel()

        // Yeni isteği başlat
        weatherJob = viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null
            _weather.value = null

            try {
                val response = repository.getCurrentWeather(
                    latitude = latitude,
                    longitude = longitude
                )

                _weather.value = response

            } catch (e: CancellationException) {
                // Coroutine iptal edildiyse iptali koru
                throw e

            } catch (e: Exception) {
                _errorMessage.value =
                    "Hava durumu alınamadı: ${e.localizedMessage ?: "Bilinmeyen hata"}"

            } finally {
                _isLoading.value = false
            }
        }
    }

}
