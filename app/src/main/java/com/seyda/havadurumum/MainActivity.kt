
package com.seyda.havadurumum

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.seyda.havadurumum.ui.CityCard
import com.seyda.havadurumum.ui.WeatherCard
import com.seyda.havadurumum.ui.theme.HavaDurumumTheme
import com.seyda.havadurumum.viewmodel.CityViewModel
import com.seyda.havadurumum.viewmodel.WeatherViewModel
import androidx.compose.foundation.layout.padding

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("HavaLifecycle", "onCreate çalıştı")

        enableEdgeToEdge()

        setContent {
            HavaDurumumTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->

                    CityListScreen(
                        modifier = Modifier.padding(padding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("HavaLifecycle", "onStart çalıştı")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HavaLifecycle", "onResume çalıştı")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HavaLifecycle", "onPause çalıştı")
    }

    override fun onStop() {
        super.onStop()
        Log.d("HavaLifecycle", "onStop çalıştı")
    }
}

@Composable
fun CityListScreen(
    modifier: Modifier = Modifier,
    cityViewModel: CityViewModel = viewModel(),
    weatherViewModel: WeatherViewModel = viewModel()
) {

    val searchText by cityViewModel.searchText.collectAsState()
    val selectedCity by cityViewModel.selectedCity.collectAsState()

    val filteredCities by cityViewModel.filteredCities.collectAsState(
        initial = emptyList()
    )

    val weather by weatherViewModel.weather.collectAsState()
    val isLoading by weatherViewModel.isLoading.collectAsState()
    val errorMessage by weatherViewModel.errorMessage.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Uygulama başlığı
        item {
            Text(
                text = "HavaDurumum",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // Açıklama
        item {
            Text(
                text = "Hava durumunu öğrenmek istediğin şehri seç.",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Yüklenme göstergesi
        if (isLoading) {
            item {
                CircularProgressIndicator()
            }
        }

        // Hata mesajı
        errorMessage?.let { message ->
            item {
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        // Seçilen şehrin hava durumu
        val currentCity = selectedCity
        val currentWeather = weather

        if (currentCity != null && currentWeather != null) {
            item {
                WeatherCard(
                    cityName = currentCity.name,
                    weather = currentWeather
                )
            }
        }

        // Şehir arama alanı
        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = { newText ->
                    cityViewModel.updateSearchText(newText)
                },
                label = {
                    Text("Şehir ara")
                },
                placeholder = {
                    Text("Örn: İstanbul, Ankara...")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Şehir listesi
        if (filteredCities.isEmpty()) {

            item {
                Text(
                    text = "Aradığın şehir bulunamadı.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        } else {

            items(
                items = filteredCities,
                key = { city -> city.plateCode }
            ) { city ->

                CityCard(
                    city = city,
                    onCityClick = { clickedCity ->

                        // Şehri seç
                        cityViewModel.selectCity(clickedCity)

                        // API'den hava durumunu getir
                        weatherViewModel.loadWeather(
                            latitude = clickedCity.latitude,
                            longitude = clickedCity.longitude
                        )

                        Log.d(
                            "CitySelection",
                            "Seçilen şehir: ${clickedCity.name}"
                        )
                    }
                )
            }
        }
    }
}
